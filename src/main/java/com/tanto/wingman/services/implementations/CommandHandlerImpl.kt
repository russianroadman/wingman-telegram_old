package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.CommandHandler
import com.tanto.wingman.services.CommandService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class CommandHandlerImpl(
    private val commandService: CommandService
) : CommandHandler {

    override fun handle(message: Message) {
        val command = commandService.getCommandFromMessage(message)
    }

}
