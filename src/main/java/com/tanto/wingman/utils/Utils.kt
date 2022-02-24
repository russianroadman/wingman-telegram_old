package com.tanto.wingman.utils

import com.tanto.wingman.data.Command
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

object Utils {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    fun getUrl(): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
    }

    fun test(): String {
        return "Testing!"
    }

    fun getCommand(string: String): Command {
        if (Command.values().map{it.toString()}.contains(string.uppercase())){
            return Command.valueOf(string.uppercase())
        }
        throw IllegalArgumentException("Command received but not recognized: {$string}")
    }

}
