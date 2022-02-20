package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

/**
 *
 * Service for reading updates from a client by bot
 * and handling them like it's a controller
 *
 */
interface UpdateHandler {

    fun onUpdateReceived(update: Update, sender: AbsSender)

}
