package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.*
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.wingman.WingmanService
import com.tanto.wingman.utils.TelegramMessagesUtils.messageHasCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender
import javax.persistence.EntityNotFoundException

@Service
class UpdateHandlerImpl(
    private val updateMessageService: UpdateMessageService,
    private val sendMessageService: SendMessageService,
    private val messageSender: MessageSender,
    private val accountFindService: AccountFindService,
    private val commandHandler: CommandHandler,
    private val wingmanService: WingmanService
) : UpdateHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun onUpdateReceived(update: Update, sender: AbsSender) {

        val message = updateMessageService.getMessage(update)
        checkAuthorities(message, sender)

    }

    private fun checkAuthorities(message: Message, sender: AbsSender){
        if (accountFindService.isExistsByChatId(updateMessageService.getChatId(message))){
            handle(message, sender)
        } else {
            authoritiesFailed(message, sender)
        }
    }

    private fun handle(message: Message, sender: AbsSender){
        if (messageHasCommand(message)){
            handleCommand(message)
        } else {
            handleMessage(message, sender)
        }
    }

    private fun authoritiesFailed(message: Message, sender: AbsSender){
        log.warn(
            "Non registered user tried to use bot - " +
                    "chat_id: ${updateMessageService.getChatId(message)}, " +
                    "user: ${updateMessageService.getSenderUsername(message)}, ${updateMessageService.getSenderFirstName(message)}, " +
                    "message text: ${updateMessageService.getMessageTextOrBlank(message)}"
        )
        val messageToSend = sendMessageService.getSendMessage(
            updateMessageService.getChatId(message),
            "Вы не зарегистрированы"
        )
        messageSender.send(sender, messageToSend)
    }

    private fun handleCommand(message: Message){
        commandHandler.handle(message)
    }

    private fun handleMessage(message: Message, sender: AbsSender){
        wingmanService.handleMessage(message, sender)
    }

    private fun testSending(update: Update, sender: AbsSender){

        val message = updateMessageService.getMessage(update)

        val chatId = message.chatId.toString()

        val admin = accountFindService.findByLogin("admin")
        val firstName = admin.name
        val surname = admin.surname

        val response = sendMessageService.getSendMessage(
            chatId,
            "Message was sent to " +
                    "$firstName $surname"
        )

        val adminResponseText = "${message.from.firstName} ${message.from.userName}:\n${message.text}"

        val adminResponse = sendMessageService.getSendMessage(
            admin.chatId,
            adminResponseText
        )

        messageSender.send(sender, response)
        messageSender.send(sender, adminResponse)
    }

    private fun testForwarding(update: Update, sender: AbsSender){

        val message = updateMessageService.getMessage(update)

        val admin = accountFindService.findByLogin("admin")

        val forwardChatId = admin.chatId
        val originalChatId = message.chatId.toString()
        val originalMessageId = message.messageId

        val forwardMessage = sendMessageService.getForwardMessage(forwardChatId, originalChatId, originalMessageId)
        val userResponse = sendMessageService.getSendMessage(
            message.chatId.toString(),
            "Message forwarded to ${admin.name} ${admin.surname}"
        )
        val chatIdResponse = sendMessageService.getSendMessage(message.chatId.toString(), message.chatId.toString())

        messageSender.forward(sender, forwardMessage)
        messageSender.send(sender, chatIdResponse)
        messageSender.send(sender, userResponse)

    }

}
