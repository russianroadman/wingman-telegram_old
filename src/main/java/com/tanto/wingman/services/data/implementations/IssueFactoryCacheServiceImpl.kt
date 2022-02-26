package com.tanto.wingman.services.data.implementations

import com.tanto.wingman.data.Command
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Department
import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.services.data.FactoryCacheService
import com.tanto.wingman.services.data.IssueService
import com.tanto.wingman.services.data.find.DepartmentFindService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class IssueFactoryCacheServiceImpl(
    private val issueService: IssueService
) : FactoryCacheService<Issue> {

    /* todo command is never used
     *  make this service for any object type, not only issue
     *   and make actions according to command
     */

    private companion object Keys {
        const val department = "department"
        const val issueName = "issueName"
    }

    private val context =
        mutableMapOf<
                UUID,
                MutableMap<String, Any>
        >()

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun putAccountToCacheWithCommand(account: Account, command: Command) {
        log.info("Issue creation by ${account.name} ${account.surname}")
        context[account.id] = mutableMapOf()
    }

    override fun fillNextStep(account: Account, item: Any) {
        log.info("Issue factory next step was called by ${account.name} ${account.surname}")
        when {
            !context[account.id]!!.containsKey(department) -> addDepartment(account, item)
            !context[account.id]!!.containsKey(issueName) -> addIssueName(account, item)
        }
    }

    override fun getReadyObject(account: Account): Issue {
        if (
            context[account.id]!!.containsKey(department) && context[account.id]!!.containsKey(issueName)
        ){
            return issueService.create(
                context[account.id]!![department] as Department,
                context[account.id]!![issueName] as String,
                account
            ).also {
                context.remove(account.id)
            }
        } else {
            throw IllegalStateException("Issue which creation was initialized by ${account.name} ${account.surname} is not ready")
        }
    }

    override fun contains(account: Account): Boolean {
        return context.containsKey(account.id)
    }

    private fun addDepartment(account: Account, departmentCandidate: Any){
        context[account.id]!![department] = departmentCandidate
    }

    private fun addIssueName(account: Account, issueNameCandidate: Any){
        context[account.id]!![issueName] = issueNameCandidate
    }

}
