package com.tanto.wingman.services;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Service for sending message to client
 */
interface TelegramMessageSenderService {

    fun send(sender: AbsSender, message: SendMessage)

    fun forward(sender: AbsSender, forwardMessage: ForwardMessage)

    fun copy(sender: AbsSender, copyMessage: CopyMessage)

    fun sendAny(sender: AbsSender, message: BotApiMethod<BotApiObject>)

}
