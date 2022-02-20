package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.Message

interface CommandHandler {

    fun handle(message: Message)

}
