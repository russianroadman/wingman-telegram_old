package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.bots.AbsSender

interface CallbackQueryHandler {

    fun handle(query: CallbackQuery, sender: AbsSender)

}
