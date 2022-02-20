package com.tanto.wingman.services.wingman

import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Wingman main business logic layer
 */
interface WingmanService {

    fun getStandardResponse(): String

    fun handleMessage(message: Message)

}
