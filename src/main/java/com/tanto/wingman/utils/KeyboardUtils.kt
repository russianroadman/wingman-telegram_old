package com.tanto.wingman.utils

import com.tanto.wingman.data.CallbackCode
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

object KeyboardUtils {

    fun getInlineKeyboardButtonWithLog(text: String, callBackText: String, callbackCode: CallbackCode): InlineKeyboardButton {
        return InlineKeyboardButton
            .builder()
            .text(text)
            .callbackData("${callbackCode}:$callBackText")
            .build()
    }

}
