package com.tanto.wingman.services.data.find

import com.tanto.wingman.data.entities.Issue
import java.util.*
import javax.persistence.EntityGraph

interface IssueFindService {

    fun findByCode(code: String): Issue

    fun findByCode(code: String, graph: EntityGraph<Issue>): Issue

    fun findById(id: UUID): Issue

    fun findById(id: UUID, graph: EntityGraph<Issue>): Issue

    fun findByClientId(id: UUID): Set<Issue>

    fun findByClientId(id: UUID, graph: EntityGraph<Issue>): Set<Issue>

    fun findByEmployeeId(id: UUID): Set<Issue>

    fun findByEmployeeId(id: UUID, graph: EntityGraph<Issue>): Set<Issue>

    fun findByDepartmentId(id: UUID): Set<Issue>

    fun findByDepartmentId(id: UUID, graph: EntityGraph<Issue>): Set<Issue>

}
