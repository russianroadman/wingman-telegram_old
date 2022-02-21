package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.UpdateMessageService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UpdateMessageServiceImpl: UpdateMessageService {

    override fun getMessage(update: Update): Message {
        return update.message
    }

    override fun getChatId(message: Message): String {
        return message.chatId.toString()
    }

    override fun getSenderUsername(message: Message): String {
        return message.from.userName
    }

    override fun getSenderFirstName(message: Message): String {
        return message.from.firstName
    }

    override fun getMessageTextOrBlank(message: Message): String {
        return if (message.hasText()) message.text else ""
    }

    override fun getMessageId(message: Message): Int {
        return message.messageId
    }

}
