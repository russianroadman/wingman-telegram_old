package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MessageRepository : JpaRepository<Message, UUID> {

    fun countByIssueId(issueId: UUID): Long

    fun countByIssueIdAndReadByReceiverIsFalse(issueId: UUID): Long

    fun findByTelegramMessageId(id: Int): Message

}
