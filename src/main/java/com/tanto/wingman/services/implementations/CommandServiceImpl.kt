package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.Command
import com.tanto.wingman.services.CommandService
import com.tanto.wingman.utils.TelegramMessagesUtils.checkIsCommand
import com.tanto.wingman.utils.TelegramMessagesUtils.messageHasCommand
import com.tanto.wingman.utils.Utils.getCommand
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity

@Service
class CommandServiceImpl : CommandService {

    override fun getCommandsFromMessage(message: Message): List<Command> {

        if (!messageHasCommand(message)) throw IllegalStateException("Message has no commands")
        val entities = message.entities.filter { checkIsCommand(it) }
        return entities.map {
            getCommandFromEntity(it)
        }

    }

    override fun getCommandFromMessage(message: Message): Command {
        return getCommandsFromMessage(message).first()
    }

    private fun getCommandFromEntity(e: MessageEntity): Command {
        return getCommand(getPureCommandFromEntity(e))
    }

    private fun getPureCommandFromEntity(e: MessageEntity): String {
        if (checkIsCommand(e)) {
            return e.text.substring(e.offset+1, e.length)
        }
        throw IllegalStateException("MessageEntity is not a command")
    }

}
