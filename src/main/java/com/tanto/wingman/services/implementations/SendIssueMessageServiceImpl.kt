package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.entities.Message
import com.tanto.wingman.services.SendIssueMessageService
import com.tanto.wingman.services.SendMessageFactory
import com.tanto.wingman.services.TelegramMessageSenderService
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.services.data.find.MessageFindService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.*

@Service
class SendIssueMessageServiceImpl(
    private val telegramMessageSenderService: TelegramMessageSenderService,
    private val sendMessageFactory: SendMessageFactory,
    private val accountFindService: AccountFindService,
    private val issueFindService: IssueFindService,
    private val messageFindService: MessageFindService,
    private val messageService: MessageService
) : SendIssueMessageService {

    // todo add sending strategy (send, copy, forward) to config

    override fun sendToEmployee(message: Message, sender: AbsSender) {

        val issue = issueFindService.findByMessageId(message.id)
        val employee = accountFindService.findEmployeeAccountByIssueId(issue.id)
        val employeeChatId = employee.chatId

        forward(employeeChatId, message, sender)

    }

    override fun sendToClient(message: Message, sender: AbsSender) {

        val issue = issueFindService.findByMessageId(message.id)
        val client = accountFindService.findClientAccountByIssueId(issue.id)
        val clientChatId = client.chatId

        copy(clientChatId, message, sender)

    }

    override fun sendToAllEmployees(message: Message, sender: AbsSender) {
        TODO("Not yet implemented")
    }

    override fun sendToAllClients(message: Message, sender: AbsSender) {
        TODO("Not yet implemented")
    }

    override fun sendToEveryone(message: Message, sender: AbsSender) {
        TODO("Not yet implemented")
    }

    override fun sendAllToClient(issueId: UUID, sender: AbsSender) {

        val messages = messageFindService.findByIssueId(issueId)
        val client = accountFindService.findClientAccountByIssueId(issueId)
        val clientChatId = client.chatId

        messages.forEach {
            forward(clientChatId, it, sender)
        }

    }

    override fun sendAllToEmployee(issueId: UUID, sender: AbsSender) {

        val messages = messageFindService.findByIssueId(issueId)
        val employee = accountFindService.findEmployeeAccountByIssueId(issueId)
        val employeeChatId = employee.chatId

        messages.forEach {
            forward(employeeChatId, it, sender)
        }

    }

    private fun send(chatId: String, message: Message, text: String, sender: AbsSender, originalChatId: Int?){
        val messageToSend = sendMessageFactory.getSendMessage(chatId, text)
        telegramMessageSenderService.send(sender, messageToSend, originalChatId)
    }

    private fun forward(chatId: String, message: Message, sender: AbsSender){
        val messageToForward = sendMessageFactory
            .getForwardMessage(chatId, message.chatId, message.telegramMessageId)
        telegramMessageSenderService.forward(sender, messageToForward)
    }

    private fun copy(chatId: String, message: Message, sender: AbsSender){
        val messageToCopy = sendMessageFactory
            .getCopyMessage(chatId, message.chatId, message.telegramMessageId)
        telegramMessageSenderService.copy(sender, messageToCopy)
    }

}
