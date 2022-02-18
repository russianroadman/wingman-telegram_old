package com.tanto.wingman.services.data

import org.telegram.telegrambots.meta.api.objects.Message

interface MessageService {

    fun saveMessageFromTelegram(message: Message): com.tanto.wingman.data.entities.Message

}
