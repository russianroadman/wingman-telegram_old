package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender

/**
 * Handler for messages from users
 */
interface MessageHandler {

    fun handleMessage(message: Message, sender: AbsSender)

}
