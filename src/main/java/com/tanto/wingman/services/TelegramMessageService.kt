package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Service for processing incoming message from update
 */
interface TelegramMessageService {

    fun getSenderUsername(message: Message): String

    fun getSenderFirstName(message: Message): String

    fun getMessageTextOrBlank(message: Message): String

}
