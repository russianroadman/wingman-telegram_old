package com.tanto.wingman.services.wingman.implementations

import com.tanto.wingman.services.wingman.WingmanService
import com.tanto.wingman.utils.TelegramMessagesUtils
import org.springframework.stereotype.Service

@Service
class WingmanServiceImpl : WingmanService {

    override fun getStandardResponse(): String {
        return TelegramMessagesUtils.getZzzEmoji()
    }

}
