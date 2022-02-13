package com.tanto.wingman.services.data

import com.tanto.wingman.data.entities.Issue
import java.util.*

interface IssueCreateService {

    fun createByUserId(id: UUID): Issue

    fun createInDepartmentByUserId(departmentId: UUID, userId: UUID): Issue

}

