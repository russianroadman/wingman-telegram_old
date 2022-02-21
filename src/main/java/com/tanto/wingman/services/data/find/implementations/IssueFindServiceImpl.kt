package com.tanto.wingman.services.data.find.implementations

import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.utils.GType
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityGraph
import javax.persistence.EntityManager

@Service
class IssueFindServiceImpl(
    private val em: EntityManager
) : IssueFindService {

    override fun findByCode(code: String): Issue {
        TODO("Not yet implemented")
    }

    override fun findByCode(code: String, graph: EntityGraph<Issue>?): Issue {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Issue {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID, graph: EntityGraph<Issue>?): Issue {
        TODO("Not yet implemented")
    }

    override fun findByClientId(id: UUID): List<Issue> {
        return findByClientId(id, null)
    }

    override fun findByClientId(id: UUID, graph: EntityGraph<Issue>?): List<Issue> {

        val query = em.createQuery("select i from issue i join i.client c where c.id = :id", Issue::class.java)
            .setParameter("id", id)

        if (graph != null){
            query.setHint(GType.LOAD, graph)
        }

        return query.resultList

    }

    override fun findByEmployeeId(id: UUID): List<Issue> {
        return findByEmployeeId(id, null)
    }

    override fun findByEmployeeId(id: UUID, graph: EntityGraph<Issue>?): List<Issue> {

        val query = em.createQuery("select i from issue i join i.employee e where e.id = :id", Issue::class.java)
            .setParameter("id", id)

        if (graph != null){
            query.setHint(GType.LOAD, graph)
        }

        return query.resultList

    }

    override fun findByDepartmentId(id: UUID): Set<Issue> {
        TODO("Not yet implemented")
    }

    override fun findByDepartmentId(id: UUID, graph: EntityGraph<Issue>?): Set<Issue> {
        TODO("Not yet implemented")
    }

    override fun findByMessageId(id: UUID): Issue {
        return findByMessageId(id, null)
    }

    override fun findByMessageId(id: UUID, graph: EntityGraph<Issue>?): Issue {

        val query = em.createQuery("select i from message m join m.issue i where m.id = :id", Issue::class.java)
            .setParameter("id", id)

        if (graph != null){
            query.setHint(GType.LOAD, graph)
        }

        return query.singleResult

    }

}
