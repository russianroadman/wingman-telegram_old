package com.tanto.wingman.services.data

import com.tanto.wingman.data.IssueStatus
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Department
import com.tanto.wingman.data.entities.Issue
import org.telegram.telegrambots.meta.api.objects.Message
import java.util.*

interface IssueService {

    fun create(department: Department, name: String, client: Account): Issue

    fun assignToEmployee(issueId: UUID, accountId: UUID)

    fun moveToEmployee(issueId: UUID, newAccountId: UUID)

    fun moveToDepartment(issueId: UUID, departmentId: UUID)

    fun setStatus(issueId: UUID, status: IssueStatus)

    fun getTimeFromLastActivity(issueId: UUID): Long

    fun getTimeFromLastActivityFromUser(issueId: UUID): Long

    fun getTimeFromLastActivityFromEmployee(issueId: UUID): Long

    fun closeIssuesDueToTimeout()

}
