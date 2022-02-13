package com.tanto.wingman.services.data

import com.tanto.wingman.data.entities.Message
import java.util.*

interface MessageCreateService {

    fun create(issueId: UUID, accountId: UUID, createdAt: Date): Message

}
