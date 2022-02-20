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

        testForwarding(update, sender)

        if (messageHasCommand(message)){
            handleCommand(message)
        } else {
            handleMessage(message)
        }

    }

    private fun handleCommand(message: Message){
        commandHandler.handle(message)
    }

    private fun handleMessage(message: Message){
        wingmanService.handleMessage(message)
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
        val originalChatId = message.chatId
        val originalMessageId = message.messageId

        val forwardMessage = sendMessageService.getForwardMessage(forwardChatId, originalChatId.toString(), originalMessageId)
        val userResponse = sendMessageService.getSendMessage(
            message.chatId.toString(),
            "Message forwarded to ${admin.name} ${admin.surname}"
        )

        messageSender.forward(sender, forwardMessage)
        messageSender.send(sender, userResponse)

    }

}
