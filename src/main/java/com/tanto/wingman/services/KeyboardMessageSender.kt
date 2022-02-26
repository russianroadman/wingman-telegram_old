package com.tanto.wingman.services

import com.tanto.wingman.data.entities.Account
import org.telegram.telegrambots.meta.bots.AbsSender

interface KeyboardMessageSender {

    fun sendIssuesListToClientChat(clientChatId: String, sender: AbsSender)

    fun sendIssuesListToClientChat(client: Account, sender: AbsSender)

    fun sendIssuesListToEmployeeChat(employeeChatId: String, sender: AbsSender)

    fun sendIssuesListToEmployeeChat(employee: Account, sender: AbsSender)

    fun sendChooseDepartmentDialogToChat(chatId: String, sender: AbsSender)

    fun sendChooseDepartmentDialogToChat(account: Account, sender: AbsSender)

}
