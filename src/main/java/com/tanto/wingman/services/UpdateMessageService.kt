package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * Service for processing incoming message from update
 */
interface UpdateMessageService {

    fun getMessage(update: Update): Message

}
