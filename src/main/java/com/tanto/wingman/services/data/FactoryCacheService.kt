package com.tanto.wingman.services.data

import com.tanto.wingman.data.Command
import com.tanto.wingman.data.entities.Account

interface FactoryCacheService<T> {

    fun putAccountToCacheWithCommand(account: Account, command: Command)

    fun fillNextStep(account: Account, item: Any)

    fun getReadyObject(account: Account): T

    fun contains(account: Account): Boolean

}
