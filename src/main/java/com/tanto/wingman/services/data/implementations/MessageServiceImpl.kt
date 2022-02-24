package com.tanto.wingman.services.data.implementations

import com.tanto.wingman.repos.MessageRepository
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message as TgMessage
import com.tanto.wingman.data.entities.Message
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*


@Service
class MessageServiceImpl(
    private val repo: MessageRepository,
    private val issueFindService: IssueFindService,
    private val accountFindService: AccountFindService
) : MessageService {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun saveMessageFromTelegram(message: TgMessage, issueId: UUID): Message {

        val msg = Message()
        val createdBy = accountFindService.findByChatId(message.chatId.toString())

        msg.issue = issueFindService.findById(issueId)
        msg.createdBy = accountFindService.findById(createdBy.id)
        val instant = Instant.ofEpochSecond(message.date.toLong())
        msg.createdAt = Date.from(instant)
        msg.chatId = message.chatId.toString()
        msg.telegramMessageId = message.messageId

        return repo.save(msg)

    }

    override fun isSentFromClient(messageId: UUID): Boolean {

        val issue = issueFindService.findByMessageId(messageId)
        val messageAccount = accountFindService.findByMessageId(messageId)
        val issueClient = accountFindService.findClientAccountByIssueId(issue.id)

        if (messageAccount.id == issueClient.id){
            return true
        }

        return false

    }

    override fun readMessageById(messageId: UUID) {
        // todo
        log.info("message considered read")
    }

}
