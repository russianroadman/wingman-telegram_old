package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

interface SendMessageWithKeyboardFactory {

    fun add(message: SendMessage, buttons: List<List<InlineKeyboardButton>>): SendMessage

    fun add(message: CopyMessage, buttons: List<List<InlineKeyboardButton>>): CopyMessage

}
