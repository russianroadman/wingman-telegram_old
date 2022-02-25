package com.tanto.wingman.services.data.find

import com.tanto.wingman.data.entities.Message
import java.util.*
import javax.persistence.EntityGraph

interface MessageFindService {

    fun findById(id: UUID): Message

    fun findById(id: UUID, graph: EntityGraph<Message>?): Message

    fun findByAccountId(id: UUID): Set<Message>

    fun findByAccountId(id: UUID, graph: EntityGraph<Message>?): Set<Message>

    fun findByIssueId(id: UUID): List<Message>

    fun findByIssueId(id: UUID, graph: EntityGraph<Message>?): List<Message>

    fun findByIssueAndAccountIds(issueId: UUID, accountId: UUID): Set<Message>

    fun findByIssueAndAccountIds(issueId: UUID, accountId: UUID, graph: EntityGraph<Message>?): Set<Message>

    fun findLatestByIssueId(id: UUID): Message

    fun findLatestByIssueId(id: UUID, graph: EntityGraph<Message>?): Message

    fun findByTelegramMessageId(id: Int): Message

    fun findByTelegramMessageId(id: Int, graph: EntityGraph<Message>?): Message

}
