package com.tanto.wingman.services.data

import org.telegram.telegrambots.meta.api.objects.Message
import java.util.*

interface MessageService {

    fun saveMessageFromTelegram(message: Message, issueId: UUID): com.tanto.wingman.data.entities.Message

    fun isSentFromClient(messageId: UUID): Boolean

}
