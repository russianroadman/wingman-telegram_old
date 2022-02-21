package com.tanto.wingman.services.wingman.implementations

import com.tanto.wingman.services.MessageSender
import com.tanto.wingman.services.SendMessageService
import com.tanto.wingman.services.UpdateMessageService
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.services.wingman.WingmanService
import com.tanto.wingman.utils.TelegramMessagesUtils
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender
import javax.persistence.EntityNotFoundException

@Service
class WingmanServiceImpl(
    private val issueFindService: IssueFindService,
    private val accountFindService: AccountFindService,
    private val messageService: MessageService,
    private val updateMessageService: UpdateMessageService,
    private val sendMessageService: SendMessageService,
    private val messageSender: MessageSender
) : WingmanService {

    override fun getStandardResponse(): String {
        return TelegramMessagesUtils.getZzzEmoji()
    }

    override fun handleMessage(message: Message, sender: AbsSender) {

        // todo maybe extract some of the logic somewhere else

        val originalMessageId = updateMessageService.getMessageId(message)
        val originalMessageChatId = updateMessageService.getChatId(message)
        val originalMessageText = updateMessageService.getMessageTextOrBlank(message)

        val senderAccount = accountFindService.findByChatId(originalMessageChatId)

        // todo message has no information what issue it belongs to and who is sending client or employee
        val issue = try {
            issueFindService.findByClientId(senderAccount.id).first()
        } catch (e: EntityNotFoundException) {
            issueFindService.findByEmployeeId(senderAccount.id).first()
        }

        val employee = accountFindService.findEmployeeAccountByIssueId(issue.id)
        val employeeChatId = employee.chatId

        val client = accountFindService.findClientAccountByIssueId(issue.id)
        val clientChatId = client.chatId

        val savedMessage = messageService.saveMessageFromTelegram(message, issue.id)

        if (messageService.sentFromClient(savedMessage.id)){

            // send to employee
            val messageToForward = sendMessageService.getForwardMessage(employeeChatId, originalMessageChatId, originalMessageId)
            messageSender.forward(sender, messageToForward)

        } else {

            // send to client
            val messageToSend = sendMessageService.getSendMessage(clientChatId, originalMessageText)
            messageSender.send(sender, messageToSend)

        }

    }

}
