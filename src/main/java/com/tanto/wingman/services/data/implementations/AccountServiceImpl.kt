package com.tanto.wingman.services.data.implementations

import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.repos.AccountRepository
import com.tanto.wingman.services.data.AccountService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(
    private val issueFindService: IssueFindService,
    private val accountFindService: AccountFindService,
    private val repo: AccountRepository
) : AccountService {

    override fun setCurrentIssueById(accountId: UUID, issueId: UUID): Account {
        val account = accountFindService.findById(accountId)
        val issue = issueFindService.findById(issueId)
        return setCurrentIssue(account, issue)
    }

    override fun setCurrentIssue(account: Account, issue: Issue): Account {
        account.current = issue
        return repo.save(account)
    }

}
