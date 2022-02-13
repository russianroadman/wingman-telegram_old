package com.tanto.wingman.controllers

import com.tanto.wingman.services.BotSessionService
import com.tanto.wingman.utils.Utils
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
class WingmanController(
    private val sessionService: BotSessionService
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

}
