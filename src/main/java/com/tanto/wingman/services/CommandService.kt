package com.tanto.wingman.services

import com.tanto.wingman.data.Command
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity

interface CommandService {

    fun getCommandsFromMessage(message: Message): List<Command>

    fun getCommandFromMessage(message: Message): Command

}
