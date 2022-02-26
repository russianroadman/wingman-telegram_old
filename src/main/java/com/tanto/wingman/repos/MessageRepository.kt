package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageRepository : JpaRepository<Message, UUID> {

    fun countByIssueId(issueId: UUID): Long

    @Query(value = "select count(m) from Message m join m.issue i where i.id = :issueId and m.readByReceiver = false and m.createdBy.id <> :targetAccountId")
    fun countUnread(issueId: UUID, targetAccountId: UUID): Long

    fun findByTelegramMessageId(id: Int): Message

}
