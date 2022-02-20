package com.tanto.wingman.services.data.find.implementations

import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.services.data.find.IssueFindService
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityGraph

@Service
class IssueFindServiceImpl : IssueFindService {

    override fun findByCode(code: String): Issue {
        TODO("Not yet implemented")
    }

    override fun findByCode(code: String, graph: EntityGraph<Issue>): Issue {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Issue {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID, graph: EntityGraph<Issue>): Issue {
        TODO("Not yet implemented")
    }

    override fun findByClientId(id: UUID): Set<Issue> {
        TODO("Not yet implemented")
    }

    override fun findByClientId(id: UUID, graph: EntityGraph<Issue>): Set<Issue> {
        TODO("Not yet implemented")
    }

    override fun findByEmployeeId(id: UUID): Set<Issue> {
        TODO("Not yet implemented")
    }

    override fun findByEmployeeId(id: UUID, graph: EntityGraph<Issue>): Set<Issue> {
        TODO("Not yet implemented")
    }

    override fun findByDepartmentId(id: UUID): Set<Issue> {
        TODO("Not yet implemented")
    }

    override fun findByDepartmentId(id: UUID, graph: EntityGraph<Issue>): Set<Issue> {
        TODO("Not yet implemented")
    }


}
