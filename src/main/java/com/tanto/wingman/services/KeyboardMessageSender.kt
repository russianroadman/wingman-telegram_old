package com.tanto.wingman.services

import com.tanto.wingman.data.entities.Account
import org.telegram.telegrambots.meta.bots.AbsSender

interface KeyboardMessageSender {

    fun sendIssuesListToClientChat(chatId: String, sender: AbsSender)

    fun sendIssuesListToClientChat(account: Account, sender: AbsSender)

    fun sendIssuesListToEmployeeChat(chatId: String, sender: AbsSender)

    fun sendIssuesListToEmployeeChat(account: Account, sender: AbsSender)

}
