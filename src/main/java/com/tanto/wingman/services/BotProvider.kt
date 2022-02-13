package com.tanto.wingman.services

import com.tanto.wingman.data.Bot

/**
 * Service for providing the only bot instance
 */
interface BotProvider {

    fun getBot(): Bot

}
