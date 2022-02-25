package com.tanto.wingman.utils

import com.tanto.wingman.data.Command
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

object KeyboardUtils {

    fun getInlineKeyboardButtonWithLog(text: String, callBackText: String, command: Command): InlineKeyboardButton {
        return InlineKeyboardButton
            .builder()
            .text(text)
            .callbackData("${command}:$callBackText")
            .build()
    }

}
