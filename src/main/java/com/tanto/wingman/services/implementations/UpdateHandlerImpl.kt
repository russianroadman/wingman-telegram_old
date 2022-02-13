package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.UpdateMessageService
import com.tanto.wingman.services.MessageSender
import com.tanto.wingman.services.SendMessageService
import com.tanto.wingman.services.UpdateHandler
import com.tanto.wingman.services.wingman.WingmanService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
final class UpdateHandlerImpl(
    private val updateMessageService: UpdateMessageService,
    private val messageSender: MessageSender,
    private val wingmanService: WingmanService,
    private val sendMessageService: SendMessageService
) : UpdateHandler {

    override fun onUpdateReceived(update: Update, sender: AbsSender) {
        val message = updateMessageService.getMessage(update)
        val responseText = wingmanService.getStandardResponse()
        val response = sendMessageService.getSendMessage(update.message.chatId.toString(), responseText)
        messageSender.send(sender, response)
    }


}
