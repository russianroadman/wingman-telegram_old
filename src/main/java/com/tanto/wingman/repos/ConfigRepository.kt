package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Config
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ConfigRepository : JpaRepository<Config, UUID> {

    fun findByKey(key: String): Config

}
