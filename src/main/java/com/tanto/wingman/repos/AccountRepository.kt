package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByLogin(login: String): Account

}
