package com.tanto.wingman.data

import com.tanto.wingman.services.UpdateHandler
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

class Bot(
    private val username: String,
    private val token: String,
    private val updateHandler: UpdateHandler
) : TelegramLongPollingBot() {

    override fun getBotUsername(): String {
        return username
    }

    override fun getBotToken(): String {
        return token
    }

    override fun onUpdateReceived(update: Update) {
        updateHandler.onUpdateReceived(update, this)
    }

}
