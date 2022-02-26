package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.util.*
import javax.transaction.Transactional

interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByLogin(login: String): Account

    fun findByChatId(chatId: String): Account

    @Modifying
    @Transactional
    @Query(value = "update Account a set a.current = null where a.id = :id and a.current is not null")
    fun emptyCurrentIssue(id: UUID)

    @Query(nativeQuery = true,
            value = "select count(*) " +
            "from message join issue i on message.issue_id = i.id " +
            "where message.read_by_receiver is false " +
            "and message.created_by_id != :id  " +
            "and :id in (i.client_id, i.employee_id)")
    fun countUnread(id: UUID): Long


}
