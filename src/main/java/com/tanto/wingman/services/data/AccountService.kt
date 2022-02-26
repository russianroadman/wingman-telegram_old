package com.tanto.wingman.services.data

import com.tanto.wingman.data.Bot
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Issue
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.*

interface AccountService {

    fun setCurrentIssueById(accountId: UUID, issueId: UUID): Account

    fun setCurrentIssue(account: Account, issue: Issue): Account

    fun emptyCurrentIssue(accountId: UUID)

    fun informAboutNewMessages(account: Account, bot: Bot)

    fun informEveryoneAboutNewMessages(bot: Bot)

}
