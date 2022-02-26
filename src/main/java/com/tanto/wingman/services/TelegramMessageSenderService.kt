package com.tanto.wingman.services;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.bots.AbsSender

/**
 * Service for sending message to client
 */
interface TelegramMessageSenderService {

    /**
     *
     * @param originalChatId used to check if message was read by receiver,
     * if message was created by application, this is not necessary, and you should pass null
     *
     * */
    fun send(sender: AbsSender, message: SendMessage, originalChatId: Int?)

    fun forward(sender: AbsSender, forwardMessage: ForwardMessage)

    fun copy(sender: AbsSender, copyMessage: CopyMessage)

    fun answer(sender: AbsSender, answer: AnswerCallbackQuery)

}
