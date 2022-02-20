package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.SendMessageService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

@Service
class SendMessageServiceImpl : SendMessageService {

    override fun getSendMessage(chatId: String, responseText: String): SendMessage {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = responseText
        return sendMessage
    }

    override fun getForwardMessage(
        forwardChatId: String,
        originalChatId: String,
        originalMessageId: Int
    ): ForwardMessage {
        return ForwardMessage(forwardChatId, originalChatId, originalMessageId)
    }

}
