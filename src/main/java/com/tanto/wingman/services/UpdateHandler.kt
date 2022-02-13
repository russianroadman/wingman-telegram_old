package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

/**
 * Service for reading updates from a client
 * It decides what to do with the update
 */
interface UpdateHandler {

    fun onUpdateReceived(update: Update, sender: AbsSender)

}
