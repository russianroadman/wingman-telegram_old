package com.tanto.wingman.utils

import org.springframework.web.servlet.support.ServletUriComponentsBuilder

object Utils {

    fun getUrl(): String {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
    }

    fun test(): String {
        return "Testing!"
    }

}
