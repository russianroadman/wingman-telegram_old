package com.tanto.wingman.services.wingman.implementations

import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.services.wingman.WingmanService
import com.tanto.wingman.utils.TelegramMessagesUtils
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class WingmanServiceImpl(
    issueFindService: IssueFindService,
    accountFindService: AccountFindService,
    messageService: MessageService
) : WingmanService {

    override fun getStandardResponse(): String {
        return TelegramMessagesUtils.getZzzEmoji()
    }

    override fun handleMessage(message: Message) {

    }

}
