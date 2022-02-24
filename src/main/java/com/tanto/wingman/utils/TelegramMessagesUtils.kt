package com.tanto.wingman.utils

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update

object TelegramMessagesUtils {

    private const val zzz = "\uD83D\uDCA4"
    private const val command = "bot_command"

    fun getZzzEmoji(): String {
        return zzz
    }

    fun getMessage(update: Update): Message{
        return update.message ?: throw IllegalStateException("Update has no message")
    }

    fun checkIsCommand(e: MessageEntity): Boolean {
        return e.type == command
    }

    fun messageHasCommand(m: Message): Boolean {
        return m.isCommand
    }

    fun getCallbackResult(query: CallbackQuery): Pair<String, String> {

        val split = query.data.split(Constraints.CALLBACK_QUERY_DELIMITER)

        if (split.size != 2) {
            throw IllegalStateException(
                "Invalid callback data, couldn't split in two with delimiter {${Constraints.CALLBACK_QUERY_DELIMITER}}" +
                        " data was: {${query.data}}"
            )
        }

        return Pair(split[0], split[1])

    }

}
