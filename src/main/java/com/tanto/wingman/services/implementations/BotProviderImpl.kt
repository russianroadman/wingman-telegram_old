package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.Bot
import com.tanto.wingman.services.BotProvider
import com.tanto.wingman.services.UpdateHandler
import com.tanto.wingman.services.config.ConfigCredentialsService
import org.springframework.stereotype.Service

@Service
class BotProviderImpl(
    conf: ConfigCredentialsService,
    updateHandler: UpdateHandler
) : BotProvider {

    private val bot = Bot(conf.getBotUsername(), conf.getBotToken(), updateHandler)

    override fun getBot(): Bot {
        return bot
    }

}
