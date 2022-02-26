package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Department
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DepartmentRepository : JpaRepository<Department, UUID> {

    fun findByName(name: String): Department

    fun findAllByNameLike(nameLike: String): List<Department>

}
