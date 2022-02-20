package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.Command
import com.tanto.wingman.services.CommandService
import com.tanto.wingman.utils.TelegramMessagesUtils.checkIsCommand
import com.tanto.wingman.utils.TelegramMessagesUtils.messageHasCommand
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.MessageEntity

@Service
class CommandServiceImpl : CommandService {

    override fun getPureCommandFromEntity(e: MessageEntity): String {
        if (checkIsCommand(e)) {
            return e.text.substring(e.offset, e.length)
        }
        throw IllegalStateException("MessageEntity is not a command")
    }

    override fun getCommandsFromMessage(message: Message): List<MessageEntity> {

        if (!messageHasCommand(message)) throw IllegalStateException("Message has no commands")
        return message.entities.filter { checkIsCommand(it) }

    }

    override fun getCommandFromMessage(message: Message): Command {
        val intersection =
            Command
                .values()
                .map { toString() }
                .intersect(getCommandsFromMessage(message).map { getPureCommandFromEntity(it) }.toSet())
        return Command.valueOf(intersection.first())
    }

}
