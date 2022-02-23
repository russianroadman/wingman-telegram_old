package com.tanto.wingman.services.data.find.implementations

import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.repos.AccountRepository
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.utils.GType
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityGraph
import javax.persistence.EntityManager
import javax.persistence.EntityNotFoundException

@Service
class AccountFindServiceImpl(
    private val repo: AccountRepository,
    private val em: EntityManager
) : AccountFindService {
    override fun findById(id: UUID): Account {
        return repo.findById(id).get()
    }

    override fun findById(id: UUID, graph: EntityGraph<Account>): Account {
        TODO("Not yet implemented")
    }

    override fun findByLogin(login: String): Account {
        return repo.findByLogin(login)
    }

    override fun findByLogin(login: String, graph: EntityGraph<Account>): Account {
        TODO("Not yet implemented")
    }

    override fun findsByFirstnameLike(name: String): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findsByFirstnameLike(name: String, graph: EntityGraph<Account>): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findsBySurnameLike(surname: String): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findsBySurnameLike(surname: String, graph: EntityGraph<Account>): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findsByFullNameLike(fullName: String): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findsByFullNameLike(fullName: String, graph: EntityGraph<Account>): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findClientAccountByIssueId(id: UUID): Account {
        return findClientAccountByIssueId(id, null)
    }

    override fun findClientAccountByIssueId(id: UUID, graph: EntityGraph<Account>?): Account {

        val query = em.createQuery("select c from Issue i join i.client c where i.id = :id", Account::class.java)
            .setParameter("id", id)

        if (graph != null){
            query.setHint(GType.LOAD, graph)
        }

        return query.singleResult

    }

    override fun findEmployeeAccountByIssueId(id: UUID): Account {
        return findEmployeeAccountByIssueId(id, null)
    }

    override fun findEmployeeAccountByIssueId(id: UUID, graph: EntityGraph<Account>?): Account {

        val query = em.createQuery("select e from Issue i join i.employee e where i.id = :id", Account::class.java)
            .setParameter("id", id)

        if (graph != null){
            query.setHint(GType.LOAD, graph)
        }

        return query.singleResult

    }

    override fun findByDepartmentId(id: UUID): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findByDepartmentId(id: UUID, graph: EntityGraph<Account>): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findByChatId(chatId: String): Account {
        return findByChatId(chatId, null)
    }

    override fun findByChatId(chatId: String, graph: EntityGraph<Account>?): Account {
        // todo
        return repo.findByChatId(chatId)
    }

    override fun isExistsByChatId(chatId: String): Boolean {
        try {
            findByChatId(chatId)
        } catch (e: Exception){
            return false
        }
        return true
    }

    override fun findByMessageId(id: UUID): Account {
        return findByMessageId(id, null)
    }

    override fun findByMessageId(id: UUID, graph: EntityGraph<Account>?): Account {

        val query = em.createQuery("select a from Message m join m.createdBy a where m.id = :id", Account::class.java)
            .setParameter("id", id)

        if (graph != null){
            query.setHint(GType.LOAD, graph)
        }

        return query.singleResult

    }

}
