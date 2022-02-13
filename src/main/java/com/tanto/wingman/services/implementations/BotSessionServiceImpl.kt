package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.BotProvider
import com.tanto.wingman.services.BotSessionService
import com.tanto.wingman.utils.Utils
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.util.logging.Logger

@Service
class BotSessionServiceImpl(
    botProvider: BotProvider
) : BotSessionService {

    private val session = TelegramBotsApi(DefaultBotSession::class.java).registerBot(botProvider.getBot())
    private val log = Logger.getLogger(this.javaClass.name)

//    @PostConstruct
//    private fun postConstruct(){
//        session.stop() // Closing session to avoid 409 error in case session was already opened on another host
//        log.info("Session was created. To open session make request to this server")
//    }

    override fun startSession() {
        if (isRunning()){
            log.info("Session is already open at ${Utils.getUrl()}")
        } else {
            session.start()
            log.info("Opened session at ${Utils.getUrl()}")
        }
    }

    override fun stopSession() {
        if (isNotRunning()){
            log.info("Session is already closed at ${Utils.getUrl()}")
        } else {
            session.stop()
            log.info("Closed session at ${Utils.getUrl()}")
        }
    }

    override fun isRunning(): Boolean {
        return session.isRunning
    }

    override fun isNotRunning(): Boolean {
        return !isRunning()
    }
}
