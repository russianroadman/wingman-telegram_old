package com.tanto.wingman.services.data.implementations

import com.tanto.wingman.data.IssueStatus
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Department
import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.repos.IssueRepository
import com.tanto.wingman.services.data.IssueService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.DepartmentFindService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@Service
class IssueServiceImpl(
    private val accountFindService: AccountFindService,
    private val repo: IssueRepository
) : IssueService {

    override fun create(department: Department, name: String, client: Account): Issue {

        // todo

        val issue = Issue()
        issue.code = name
        issue.department = department
        val instant = Instant.now()
        issue.createdAt = Date.from(instant)
        issue.acceptedAt = Date.from(instant)
        issue.client = client
        issue.employee = accountFindService.findByDepartmentId(department.id).first()
        issue.status = IssueStatus.IN_PROGRESS
        issue.closedAt = null

        return repo.save(issue)

    }

    override fun assignToEmployee(issueId: UUID, accountId: UUID) {
        TODO("Not yet implemented")
    }

    override fun moveToEmployee(issueId: UUID, newAccountId: UUID) {
        TODO("Not yet implemented")
    }

    override fun moveToDepartment(issueId: UUID, departmentId: UUID) {
        TODO("Not yet implemented")
    }

    override fun setStatus(issueId: UUID, status: IssueStatus) {
        TODO("Not yet implemented")
    }

    override fun getTimeFromLastActivity(issueId: UUID): Long {
        TODO("Not yet implemented")
    }

    override fun getTimeFromLastActivityFromUser(issueId: UUID): Long {
        TODO("Not yet implemented")
    }

    override fun getTimeFromLastActivityFromEmployee(issueId: UUID): Long {
        TODO("Not yet implemented")
    }

    override fun closeIssuesDueToTimeout() {
        TODO("Not yet implemented")
    }

}
