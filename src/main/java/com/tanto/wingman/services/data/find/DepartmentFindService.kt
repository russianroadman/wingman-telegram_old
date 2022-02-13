package com.tanto.wingman.services.data.find

import com.tanto.wingman.data.entities.Department
import java.util.*

interface DepartmentFindService {

    fun findById(id: UUID): Department

    fun findByNameExact(name: String): Department

    fun findByNameLike(name: String): Set<Department>

    fun findByIssueId(id: UUID): Department

    fun findByAccountId(id: UUID): Set<Department>

}
