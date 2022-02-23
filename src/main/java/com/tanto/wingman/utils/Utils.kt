package com.tanto.wingman.utils

import com.tanto.wingman.data.Command
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

object Utils {

    fun getUrl(): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
    }

    fun test(): String {
        return "Testing!"
    }

    fun getCommand(string: String): Command {
        return Command.valueOf(string.uppercase())
    }

}
