package com.tanto.wingman.services;

import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Service for sending message to client
 */
interface MessageSender {

    fun send(sender: AbsSender, message: SendMessage)

    fun forward(sender: AbsSender, forwardMessage: ForwardMessage)

}
