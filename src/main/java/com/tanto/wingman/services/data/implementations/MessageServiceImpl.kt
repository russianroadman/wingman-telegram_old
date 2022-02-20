package com.tanto.wingman.services.data.implementations

import com.tanto.wingman.repos.MessageRepository
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import java.time.Instant
import java.util.*


@Service
class MessageServiceImpl(
    private val repo: MessageRepository,
    private val issueFindService: IssueFindService,
    private val accountFindService: AccountFindService
) : MessageService {

    override fun saveMessageFromTelegram(message: Message, issueId: UUID): com.tanto.wingman.data.entities.Message {

        val msg = com.tanto.wingman.data.entities.Message()
        val createdBy = accountFindService.findByChatId(message.chatId.toString())

        msg.issue = issueFindService.findById(issueId)
        msg.createdBy = accountFindService.findById(createdBy.id)
        val instant = Instant.ofEpochSecond(message.date.toLong())
        msg.createdAt = Date.from(instant)
        msg.chatId = message.chatId.toString()
        msg.telegramMessageId = message.messageId

        return repo.save(msg)

    }

}
