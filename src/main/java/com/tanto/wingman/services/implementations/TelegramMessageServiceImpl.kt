package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.TelegramMessageService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class TelegramMessageServiceImpl: TelegramMessageService {

    override fun getSenderUsername(message: Message): String {
        return message.from.userName
    }

    override fun getSenderFirstName(message: Message): String {
        return message.from.firstName
    }

    override fun getMessageTextOrBlank(message: Message): String {
        return if (message.hasText()) message.text else ""
    }

}
