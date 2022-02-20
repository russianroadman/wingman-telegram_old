package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.MessageSender
import com.tanto.wingman.services.SendMessageService
import com.tanto.wingman.services.UpdateHandler
import com.tanto.wingman.services.UpdateMessageService
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class UpdateHandlerImpl(
    private val messageService: MessageService,
    private val updateMessageService: UpdateMessageService,
    private val sendMessageService: SendMessageService,
    private val messageSender: MessageSender,
    private val accountFindService: AccountFindService
) : UpdateHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun onUpdateReceived(update: Update, sender: AbsSender) {

//        testSending(update, sender)
        testForwarding(update, sender)

    }

    private fun saveMessage(update: Update){

    }


    private fun testSending(update: Update, sender: AbsSender){
        val message = updateMessageService.getMessage(update)
        val chatId = update.message.chatId.toString()

        val admin = accountFindService.findByLogin("admin")
        val firstName = admin.name
        val surname = admin.surname

        val response = sendMessageService.getSendMessage(
            chatId,
            "Message was sent to " +
                    "$firstName $surname"
        )

        val adminResponseText = "${update.message.from.firstName} ${update.message.from.userName}:\n${update.message.text}"

        val adminResponse = sendMessageService.getSendMessage(
            admin.chatId,
            adminResponseText
        )

        messageSender.send(sender, response)
        messageSender.send(sender, adminResponse)
    }

    private fun testForwarding(update: Update, sender: AbsSender){

        val admin = accountFindService.findByLogin("admin")

        val forwardChatId = admin.chatId
        val originalChatId = update.message.chatId
        val originalMessageId = update.message.messageId

        val forwardMessage = sendMessageService.getForwardMessage(forwardChatId, originalChatId.toString(), originalMessageId)
        val userResponse = sendMessageService.getSendMessage(
            update.message.chatId.toString(),
            "Message forwarded to ${admin.name} ${admin.surname}"
        )

        messageSender.forward(sender, forwardMessage)
        messageSender.send(sender, userResponse)

    }

}
