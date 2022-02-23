package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.SendMessageWithKeyboardFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

@Service
class SendMessageWithKeyboardFactoryImpl : SendMessageWithKeyboardFactory {

    override fun add(message: SendMessage, buttons: List<List<InlineKeyboardButton>>): SendMessage {
        return SendMessage
            .builder()
            .chatId(message.chatId)
            .text(message.text)
            .replyMarkup(
                InlineKeyboardMarkup.builder().keyboard(buttons).build()
            )
            .build()
    }

    override fun add(message: CopyMessage, buttons: List<List<InlineKeyboardButton>>): CopyMessage {
        return CopyMessage
            .builder()
            .messageId(message.messageId)
            .chatId(message.chatId)
            .fromChatId(message.fromChatId)
            .replyMarkup(
                InlineKeyboardMarkup.builder().keyboard(buttons).build()
            )
            .build()
    }

}
