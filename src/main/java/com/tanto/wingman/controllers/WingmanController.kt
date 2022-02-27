package com.tanto.wingman.controllers

import com.tanto.wingman.services.BotProvider
import com.tanto.wingman.services.BotSessionService
import com.tanto.wingman.services.data.AccountService
import com.tanto.wingman.utils.AppConstants
import com.tanto.wingman.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.telegram.telegrambots.meta.bots.AbsSender

@Controller
class WingmanController(
    private val sessionService: BotSessionService,
    private val accountService: AccountService,
    private val botProvider: BotProvider
) {

    @GetMapping("/")
    fun blankPage(): String {
        return "blank"
    }

    @ResponseBody
    @GetMapping("/test")
    fun test(): String{
        return Utils.test()
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/start")
    fun startBot(){
        sessionService.startSession()
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/stop")
    fun stopBot(){
        sessionService.stopSession()
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/informAllUnreadMessages")
    @Scheduled(cron = AppConstants.CRON_EVERY_30_MIN)
    fun informAllUnreadMessages(){
        accountService.informEveryoneAboutNewMessages(botProvider.getBot())
    }

}
