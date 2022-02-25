package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.data.entities.Message
import com.tanto.wingman.services.KeyboardMessageSender
import com.tanto.wingman.services.SendIssueMessageService
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.services.MessageHandler
import com.tanto.wingman.utils.TelegramMessagesUtils
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message as TgMessage
import org.telegram.telegrambots.meta.bots.AbsSender
import javax.persistence.NoResultException

@Service
class MessageHandlerImpl(
    private val issueFindService: IssueFindService,
    private val accountFindService: AccountFindService,
    private val messageService: MessageService,
    private val sendIssueMessageService: SendIssueMessageService,
    private val keyboardMessageSender: KeyboardMessageSender
) : MessageHandler {

    override fun handleMessage(message: TgMessage, sender: AbsSender) {

        val originalMessageChatId = message.chatId.toString()
        val senderAccount = accountFindService.findByChatId(originalMessageChatId)

        val issue = findIssueOrSendIssuesMenu(senderAccount, sender)

        val savedMessage = messageService.saveMessageFromTelegram(message, issue.id)

        if (messageService.isSentFromClient(savedMessage.id)){
            sentFromClient(savedMessage, sender)
        } else {
            sentFromEmployee(savedMessage, sender)
        }

    }

    private fun sentFromClient(message: Message, sender: AbsSender){

        val issue = issueFindService.findByMessageId(message.id)
        val employee = accountFindService.findEmployeeAccountByIssueId(issue.id)

        // todo exceptions when employee current issue is null
        val currentEmployeeIssue = issueFindService.findCurrentByAccountId(employee.id)

        if (currentEmployeeIssue.id == issue.id){
            sendIssueMessageService.sendToEmployee(message, sender)
        }

    }

    private fun sentFromEmployee(message: Message, sender: AbsSender){

        val issue = issueFindService.findByMessageId(message.id)
        val client = accountFindService.findClientAccountByIssueId(issue.id)
        val currentClientIssue = issueFindService.findCurrentByAccountId(client.id)

        if (currentClientIssue.id == issue.id){
            sendIssueMessageService.sendToClient(message, sender)
        }

    }

    private fun getServiceIsDownText(): String {
        return TelegramMessagesUtils.getZzzEmoji()
    }

    private fun findIssueOrSendIssuesMenu(account: Account, sender: AbsSender): Issue {

        // todo in this case better popup "new issue" dialog

        try {
            return issueFindService.findCurrentByAccountId(account.id)
        } catch (e: NoResultException){
            if (account.client) {
                keyboardMessageSender.sendIssuesListToClientChat(account, sender)
            } else {
                keyboardMessageSender.sendIssuesListToEmployeeChat(account, sender)
            }
            throw NoResultException("Message was sent, but no current issue was selected by user ${account.name} ${account.surname}")
        }

    }

}
