package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.Command
import com.tanto.wingman.services.CallbackQueryHandler
import com.tanto.wingman.services.SendMessageFactory
import com.tanto.wingman.services.TelegramMessageSenderService
import com.tanto.wingman.services.data.AccountService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.services.data.find.MessageFindService
import com.tanto.wingman.utils.TelegramMessagesUtils.getCallbackResult
import com.tanto.wingman.utils.Utils.getCommand
import com.tanto.wingman.utils.Utils.getDecoratedIssueName
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class CallbackQueryHandlerImpl(
    private val accountService: AccountService,
    private val accountFindService: AccountFindService,
    private val issueFindService: IssueFindService,
    private val messageFindService: MessageFindService,
    private val senderService: TelegramMessageSenderService,
    private val messageFactory: SendMessageFactory
) : CallbackQueryHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun handle(query: CallbackQuery, sender: AbsSender) {

        log.info("Handling CallbackQuery, data is {${query.data}}")

        val result = getCallbackResult(query)
        val command = getCommand(result.first)
        val issueCode = result.second
        val message = query.message

        when (command) {
            Command.ISSUES -> handleIssueListQuery(issueCode, message, sender)
            else -> {}
        }

    }

    private fun handleIssueListQuery(issueCode: String, message: Message, sender: AbsSender){

        val issue = issueFindService.findByCode(issueCode)
        val account = accountFindService.findByChatId(message.chatId.toString())
        accountService.setCurrentIssue(account, issue)
        val messages = messageFindService.findByIssueId(issue.id)

        senderService.send(sender, SendMessage(message.chatId.toString(), getDecoratedIssueName(issue.code)), null)

        messages.forEach {
            if (it.chatId == account.chatId){
                senderService.forward(
                    sender,
                    messageFactory.getForwardMessage(
                        message.chatId.toString(),
                        it.chatId,
                        it.telegramMessageId
                    )
                )
            } else {
                senderService.copy(
                    sender,
                    messageFactory.getCopyMessage(
                        message.chatId.toString(),
                        it.chatId,
                        it.telegramMessageId
                    )
                )
            }
        }

    }

}
