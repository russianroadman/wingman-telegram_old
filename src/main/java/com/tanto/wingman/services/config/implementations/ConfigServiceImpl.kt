package com.tanto.wingman.services.config.implementations

import com.tanto.wingman.repos.ConfigRepository
import com.tanto.wingman.services.config.ConfigService
import org.springframework.stereotype.Service

@Service
class ConfigServiceImpl(
    private val repository: ConfigRepository
) : ConfigService {

    override fun getStringValue(key: String): String {
        return repository.findByKey(key).value
    }

    override fun getIntegerValue(key: String): Int {
        return repository.findByKey(key).value.toInt()
    }

    override fun getDoubleValue(key: String): Double {
        return repository.findByKey(key).value.toDouble()
    }

    override fun getBooleanValue(key: String): Boolean {
        return repository.findByKey(key).value.toBoolean()
    }

    override fun getLongValue(key: String): Long {
        return repository.findByKey(key).value.toLong()
    }

}
