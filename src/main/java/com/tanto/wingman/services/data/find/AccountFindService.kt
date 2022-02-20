package com.tanto.wingman.services.data.find

import com.tanto.wingman.data.entities.Account
import java.util.*
import javax.persistence.EntityGraph

interface AccountFindService {

    fun findById(id: UUID): Account

    fun findById(id: UUID, graph: EntityGraph<Account>): Account

    fun findByLogin(login: String): Account

    fun findByLogin(login: String, graph: EntityGraph<Account>): Account

    fun findsByFirstnameLike(name: String): Set<Account>

    fun findsByFirstnameLike(name: String, graph: EntityGraph<Account>): Set<Account>

    fun findsBySurnameLike(surname: String): Set<Account>

    fun findsBySurnameLike(surname: String, graph: EntityGraph<Account>): Set<Account>

    fun findsByFullNameLike(fullName: String): Set<Account>

    fun findsByFullNameLike(fullName: String, graph: EntityGraph<Account>): Set<Account>

    fun findClientAccountByIssueId(id: UUID): Account

    fun findClientAccountByIssueId(id: UUID, graph: EntityGraph<Account>): Account

    fun findEmployeeAccountByIssueId(id: UUID): Account

    fun findEmployeeAccountByIssueId(id: UUID, graph: EntityGraph<Account>): Account

    fun findByDepartmentId(id: UUID): Set<Account>

    fun findByDepartmentId(id: UUID, graph: EntityGraph<Account>): Set<Account>

    fun findByChatId(chatId: String): Account

    fun findByChatId(chatId: String, graph: EntityGraph<Account>): Account

}
