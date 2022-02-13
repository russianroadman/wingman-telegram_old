package com.tanto.wingman.services.config.implementations

import com.tanto.wingman.services.config.ConfigCredentialsService
import com.tanto.wingman.services.config.ConfigService
import org.springframework.stereotype.Service

@Service
class ConfigCredentialsServiceImpl(
    private val configService: ConfigService
) : ConfigCredentialsService {

    companion object Keys {

        const val botToken = "bot_token"
        const val botUsername = "bot_username"

    }

    override fun getBotToken(): String {
        return configService.getStringValue(botToken)
    }

    override fun getBotUsername(): String {
        return configService.getStringValue(botUsername)
    }

}
