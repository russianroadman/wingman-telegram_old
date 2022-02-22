package com.tanto.wingman.services.data.find.implementations

import com.tanto.wingman.data.entities.Message
import com.tanto.wingman.repos.MessageRepository
import com.tanto.wingman.services.data.find.MessageFindService
import com.tanto.wingman.utils.GType
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityGraph
import javax.persistence.EntityManager

@Service
class MessageFindServiceImpl(
    private val repo: MessageRepository,
    private val em: EntityManager
) : MessageFindService {

    override fun findById(id: UUID): Message {
        return findById(id, null)
    }

    override fun findById(id: UUID, graph: EntityGraph<Message>?): Message {

        val query = em.createQuery("select m from Message m where m.id = :id", Message::class.java)
            .setParameter("id", id)

        if (graph != null) {
            query.setHint(GType.LOAD, graph)
        }

        return query.singleResult

    }

    override fun findByAccountId(id: UUID): Set<Message> {
        TODO("Not yet implemented")
    }

    override fun findByAccountId(id: UUID, graph: EntityGraph<Message>?): Set<Message> {
        TODO("Not yet implemented")
    }

    override fun findByIssueId(id: UUID): List<Message> {
        return findByIssueId(id, null)
    }

    override fun findByIssueId(id: UUID, graph: EntityGraph<Message>?): List<Message> {

        val query = em.createQuery("select m from Message m join m.issue i where i.id = :id", Message::class.java)
            .setParameter("id", id)

        if (graph != null) {
            query.setHint(GType.LOAD, graph)
        }

        return query.resultList

    }

    override fun findByIssueAndAccountIds(issueId: UUID, accountId: UUID): Set<Message> {
        TODO("Not yet implemented")
    }

    override fun findByIssueAndAccountIds(issueId: UUID, accountId: UUID, graph: EntityGraph<Message>?): Set<Message> {
        TODO("Not yet implemented")
    }

    override fun findLatestByIssueId(id: UUID): Message {
        TODO("Not yet implemented")
    }

    override fun findLatestByIssueId(id: UUID, graph: EntityGraph<Message>?): Message {
        TODO("Not yet implemented")
    }
}
