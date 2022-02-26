package com.tanto.wingman.services.data.find.implementations

import com.tanto.wingman.data.entities.Department
import com.tanto.wingman.repos.DepartmentRepository
import com.tanto.wingman.services.data.find.DepartmentFindService
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager

@Service
class DepartmentFindServiceImpl(
    private val repo: DepartmentRepository,
    private val em: EntityManager
) : DepartmentFindService {

    override fun findById(id: UUID): Department {
        return repo.findById(id).get()
    }

    override fun findByNameExact(name: String): Department {
        return repo.findByName(name)
    }

    override fun findByNameLike(name: String): List<Department> {
        return repo.findAllByNameLike(name)
    }

    override fun findByIssueId(id: UUID): Department {

        return em.createQuery("select d from Issue i join i.department d where i.id = :id", Department::class.java)
            .setParameter("id", id).singleResult

    }

    override fun findByAccountId(id: UUID): List<Department> {

        return em.createQuery("select d from Account a join a.departments d where a.id = :id", Department::class.java)
            .setParameter("id", id).resultList

    }

}
