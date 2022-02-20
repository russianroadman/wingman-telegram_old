package com.tanto.wingman.services.data.find.implementations

import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.repos.AccountRepository
import com.tanto.wingman.services.data.find.AccountFindService
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityGraph
import javax.persistence.EntityNotFoundException

@Service
class AccountFindServiceImpl(
    private val repo: AccountRepository
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
        TODO("Not yet implemented")
    }

    override fun findClientAccountByIssueId(id: UUID, graph: EntityGraph<Account>): Account {
        TODO("Not yet implemented")
    }

    override fun findEmployeeAccountByIssueId(id: UUID): Account {
        TODO("Not yet implemented")
    }

    override fun findEmployeeAccountByIssueId(id: UUID, graph: EntityGraph<Account>): Account {
        TODO("Not yet implemented")
    }

    override fun findByDepartmentId(id: UUID): Set<Account> {
        TODO("Not yet implemented")
    }

    override fun findByDepartmentId(id: UUID, graph: EntityGraph<Account>): Set<Account> {
        TODO("Not yet implemented")
    }
}
