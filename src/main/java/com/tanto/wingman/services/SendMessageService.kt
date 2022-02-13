package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface SendMessageService {

    fun getSendMessage(chatId: String, responseText: String): SendMessage

}
