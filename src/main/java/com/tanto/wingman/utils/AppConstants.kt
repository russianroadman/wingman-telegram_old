package com.tanto.wingman.utils

import org.telegram.telegrambots.meta.api.methods.ParseMode

object AppConstants {

    const val CALLBACK_QUERY_DELIMITER = ":"
    const val PARSE_MODE_MARKDOWN = ParseMode.MARKDOWN
    const val CHOOSE_ISSUE = "Выберите обращение из списка"
    const val YOU_HAVE_UNREAD_MESSAGES = "У вас есть непрочитанные сообщения"
    const val CRON_EVERY_30_MIN = "0 0/30 * * * ?"

}
