package com.tanto.wingman.services

import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

/**
 *
 * Responsibility of this interface is to get message that is fully prepared to be sent
 *
 * */
interface SendMessageFactory {

    fun getSendMessage(chatId: String, responseText: String): SendMessage

    fun getForwardMessage(forwardChatId: String, originalChatId: String, originalMessageId: Int): ForwardMessage

    fun getCopyMessage(copyChatId: String, originalChatId: String, originalMessageId: Int): CopyMessage

}
