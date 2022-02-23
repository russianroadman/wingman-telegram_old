package com.tanto.wingman.services.data

import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Issue
import java.util.*

interface AccountService {

    fun setCurrentIssueById(accountId: UUID, issueId: UUID): Account

    fun setCurrentIssue(account: Account, issue: Issue): Account

}
