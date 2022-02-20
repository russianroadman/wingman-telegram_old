package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Message
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MessageRepository : JpaRepository<Message, UUID> {
}
