package com.tanto.wingman.services.data

import com.tanto.wingman.data.IssueStatus
import java.util.*

interface IssueService {

    fun assignToEmployee(issueId: UUID, accountId: UUID)

    fun moveToEmployee(issueId: UUID, newAccountId: UUID)

    fun moveToDepartment(issueId: UUID, departmentId: UUID)

    fun setStatus(issueId: UUID, status: IssueStatus)

    fun getTimeFromLastActivity(issueId: UUID): Long

    fun getTimeFromLastActivityFromUser(issueId: UUID): Long

    fun getTimeFromLastActivityFromEmployee(issueId: UUID): Long

    fun closeIssuesDueToTimeout()

}
