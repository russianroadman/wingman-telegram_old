package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender

interface CommandHandler {

    fun handle(message: Message, sender: AbsSender)

}
