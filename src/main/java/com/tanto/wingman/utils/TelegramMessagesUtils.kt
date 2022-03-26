package com.tanto.wingman.utils

import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

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

        val split = query.data.split(AppConstants.CALLBACK_QUERY_DELIMITER)

        if (split.size != 2) {
            throw IllegalStateException(
                "Invalid callback data, couldn't split in two with delimiter {${AppConstants.CALLBACK_QUERY_DELIMITER}}" +
                        " data was: {${query.data}}"
            )
        }

        return Pair(split[0], split[1])

    }

    fun getSenderUsername(message: Message): String {
        return message.from.userName
    }

    fun getSenderFirstName(message: Message): String {
        return message.from.firstName
    }

    fun getMessageTextOrBlank(message: Message): String {
        return if (message.hasText()) message.text else ""
    }

    fun getDecoratedIssueName(text: String): String {
        return "$text \uD83D\uDCAC"
    }

    fun addKeyboard(message: SendMessage, buttons: List<List<InlineKeyboardButton>>): SendMessage {
        return SendMessage
            .builder()
            .chatId(message.chatId)
            .text(message.text)
            .replyMarkup(
                InlineKeyboardMarkup.builder().keyboard(buttons).build()
            )
            .build()
    }

    fun addKeyboard(message: CopyMessage, buttons: List<List<InlineKeyboardButton>>): CopyMessage {
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

    fun bold(text: String): String {
        return "**${text}**"
    }

    fun italic(text: String): String {
        return "__${text}__"
    }

    fun code(text: String): String {
        return "```${text}```"
    }

    fun strikethrough(text: String): String {
        return "~~${text}~~"
    }

}
