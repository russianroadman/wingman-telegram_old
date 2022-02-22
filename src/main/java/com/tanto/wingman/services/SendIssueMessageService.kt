package com.tanto.wingman.services

import com.tanto.wingman.data.entities.Message
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.*

/**
 *
 * Responsibility of this service is how message will be sent
 *
 * */
interface SendIssueMessageService {

    fun sendToEmployee(message: Message, sender: AbsSender)

    fun sendToClient(message: Message, sender: AbsSender)

    fun sendToAllEmployees(message: Message, sender: AbsSender)

    fun sendToAllClients(message: Message, sender: AbsSender)

    fun sendToEveryone(message: Message, sender: AbsSender)

    fun sendAllToEmployee(issueId: UUID, sender: AbsSender)

    fun sendAllToClient(issueId: UUID, sender: AbsSender)

}
