package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.TelegramMessageSenderService
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.MessageFindService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.CopyMessage
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class TelegramMessageSenderServiceImpl(
    private val messageService: MessageService,
    private val messageFindService: MessageFindService
) : TelegramMessageSenderService {

    override fun send(sender: AbsSender, message: SendMessage, originalChatId: Int?) {
        sender.execute(message)
        if (originalChatId != null) read(originalChatId)
    }

    override fun forward(sender: AbsSender, forwardMessage: ForwardMessage) {
        sender.execute(forwardMessage)
        read(forwardMessage.messageId)
    }

    override fun copy(sender: AbsSender, copyMessage: CopyMessage) {
        sender.execute(copyMessage)
        read(copyMessage.messageId)
    }

    override fun answer(sender: AbsSender, answer: AnswerCallbackQuery) {
        sender.execute(answer)
    }

    private fun read(chatId: Int){
        messageService.readMessageById(messageFindService.findByTelegramMessageId(chatId).id)
    }

}
