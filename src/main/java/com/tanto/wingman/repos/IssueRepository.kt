package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Issue
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface IssueRepository : JpaRepository<Issue, UUID> {

    fun findByCode(code: String): Issue

}
