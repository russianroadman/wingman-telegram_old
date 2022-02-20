package com.tanto.wingman.utils

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update

object TelegramMessagesUtils {

    fun getZzzEmoji(): String {
        return "\uD83D\uDCA4"
    }

    fun getMessage(update: Update): Message{
        return update.message ?: throw IllegalStateException("Update has no message")
    }

    fun getCommandEntityName(): String{
        return "bot_command"
    }

    fun checkIsCommand(e: MessageEntity): Boolean {
        return e.type == TelegramMessagesUtils.getCommandEntityName()
    }

    fun messageHasCommand(m: Message): Boolean {
        return m.isCommand
    }

}
