package com.tanto.wingman.services

/**
 * Service for handling session that defines if bot active or down
 */
interface BotSessionService {

    fun startSession()

    fun stopSession()

    fun isRunning(): Boolean

    fun isNotRunning(): Boolean

}
