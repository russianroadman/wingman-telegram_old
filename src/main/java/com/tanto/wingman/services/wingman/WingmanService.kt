package com.tanto.wingman.services.wingman

import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender

/**
 * Wingman main business logic layer
 */
interface WingmanService {

    fun getStandardResponse(): String

    /**
     * Issue and sender account retrieved from message.
     * Message saved to the database.
     * If it's a client message then sent to employee attached to this issue if needed.
     * Otherwise, sent to employee if needed.
     * */
    fun handleMessage(message: Message, sender: AbsSender)

}
