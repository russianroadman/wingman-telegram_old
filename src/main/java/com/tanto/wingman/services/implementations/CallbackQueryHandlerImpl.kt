package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.CallbackCode
import com.tanto.wingman.services.CallbackQueryHandler
import com.tanto.wingman.services.SendMessageFactory
import com.tanto.wingman.services.TelegramMessageSenderService
import com.tanto.wingman.services.data.AccountService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.DepartmentFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.services.data.find.MessageFindService
import com.tanto.wingman.services.data.implementations.IssueFactoryCacheServiceImpl
import com.tanto.wingman.utils.TelegramMessagesUtils.getCallbackResult
import com.tanto.wingman.utils.TelegramMessagesUtils.getDecoratedIssueName
import com.tanto.wingman.utils.Utils.getCallbackCode
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
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
    private val messageFactory: SendMessageFactory,
    private val messageSenderService: TelegramMessageSenderService,
    private val issueFactoryCacheServiceImpl: IssueFactoryCacheServiceImpl,
    private val departmentFindService: DepartmentFindService
) : CallbackQueryHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun handle(query: CallbackQuery, sender: AbsSender) {

        log.info("Handling CallbackQuery, data is {${query.data}}")
        messageSenderService.answer(sender, AnswerCallbackQuery(query.id))

        val result = getCallbackResult(query)
        val callbackCode = getCallbackCode(result.first)
        val callbackText = result.second
        val message = query.message

        when (callbackCode) {
            CallbackCode.GET_ISSUE_BY_CODE -> sendIssueContentToSender(callbackText, message, sender)
            CallbackCode.CHOOSE_DEPARTMENT_FOR_NEW_ISSUE -> sendChooseNameOfIssueDialogToSender(callbackText, message, sender)
//            else -> {}
        }

    }

    private fun sendIssueContentToSender(issueCode: String, message: Message, sender: AbsSender){

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

    private fun sendChooseNameOfIssueDialogToSender(departmentName: String, message: Message, sender: AbsSender){

        issueFactoryCacheServiceImpl.fillNextStep(
            accountFindService.findByChatId(message.chatId.toString()),
            departmentFindService.findByNameExact(departmentName)
        )

        val sendMessage = SendMessage()
        sendMessage.chatId = message.chatId.toString()
        sendMessage.text = "Название обращения (не более 15 символов):"

        messageSenderService.send(
            sender,
            sendMessage,
            null
        )

    }

}
