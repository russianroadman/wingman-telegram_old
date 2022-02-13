package com.tanto.wingman.services.config

interface ConfigService {

    fun getStringValue(key: String): String

    fun getIntegerValue(key: String): Int

    fun getDoubleValue(key: String): Double

    fun getBooleanValue(key: String): Boolean

    fun getLongValue(key: String): Long

}
