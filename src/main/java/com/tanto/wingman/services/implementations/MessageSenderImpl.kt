package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.MessageSender
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class MessageSenderImpl : MessageSender {

    override fun send(sender: AbsSender, message: SendMessage) {
        sender.execute(message)
    }

}
