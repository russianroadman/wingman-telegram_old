package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.*
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.wingman.WingmanService
import com.tanto.wingman.utils.TelegramMessagesUtils.messageHasCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class UpdateHandlerImpl(
    private val telegramMessageService: TelegramMessageService,
    private val sendMessageFactory: SendMessageFactory,
    private val telegramMessageSenderService: TelegramMessageSenderService,
    private val accountFindService: AccountFindService,
    private val commandHandler: CommandHandler,
    private val wingmanService: WingmanService
) : UpdateHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun onUpdateReceived(update: Update, sender: AbsSender) {

        val message = telegramMessageService.getMessage(update)
        checkAuthorities(message, sender)

    }

    private fun checkAuthorities(message: Message, sender: AbsSender){
        if (accountFindService.isExistsByChatId(telegramMessageService.getChatId(message))){
            handle(message, sender)
        } else {
            authoritiesFailed(message, sender)
        }
    }

    private fun handle(message: Message, sender: AbsSender){
        if (messageHasCommand(message)){
            handleCommand(message)
        } else {
            handleMessage(message, sender)
        }
    }

    private fun authoritiesFailed(message: Message, sender: AbsSender){
        log.warn(
            "Non registered user tried to use bot - " +
                    "chat_id: ${telegramMessageService.getChatId(message)}, " +
                    "user: ${telegramMessageService.getSenderUsername(message)}, ${telegramMessageService.getSenderFirstName(message)}, " +
                    "message text: ${telegramMessageService.getMessageTextOrBlank(message)}"
        )
        val messageToSend = sendMessageFactory.getSendMessage(
            telegramMessageService.getChatId(message),
            "Вы не зарегистрированы"
        )
        telegramMessageSenderService.send(sender, messageToSend)
    }

    private fun handleCommand(message: Message){
        commandHandler.handle(message)
    }

    private fun handleMessage(message: Message, sender: AbsSender){
        wingmanService.handleMessage(message, sender)
    }

}
