package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.UpdateMessageService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UpdateMessageServiceImpl: UpdateMessageService {

    override fun getMessage(update: Update): Message {
        return update.message
    }

}
