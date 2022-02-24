package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.TelegramMessageSenderService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.MessageId
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class TelegramMessageSenderServiceImpl : TelegramMessageSenderService {

    override fun send(sender: AbsSender, message: SendMessage) {
        sender.execute(message)
    }

    override fun forward(sender: AbsSender, forwardMessage: ForwardMessage) {
        sender.execute(forwardMessage)
    }

    override fun copy(sender: AbsSender, copyMessage: CopyMessage) {
        sender.execute(copyMessage)
    }

    override fun sendAny(sender: AbsSender, message: BotApiMethod<BotApiObject>) {
        sender.execute(message)
    }

}
