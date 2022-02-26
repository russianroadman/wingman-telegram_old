package com.tanto.wingman.services.implementations

import com.tanto.wingman.services.*
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.MessageHandler
import com.tanto.wingman.services.data.AccountService
import com.tanto.wingman.utils.TelegramMessagesUtils.getMessage
import com.tanto.wingman.utils.TelegramMessagesUtils.getMessageTextOrBlank
import com.tanto.wingman.utils.TelegramMessagesUtils.getSenderFirstName
import com.tanto.wingman.utils.TelegramMessagesUtils.getSenderUsername
import com.tanto.wingman.utils.TelegramMessagesUtils.messageHasCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class UpdateHandlerImpl(
    private val sendMessageFactory: SendMessageFactory,
    private val telegramMessageSenderService: TelegramMessageSenderService,
    private val accountFindService: AccountFindService,
    private val commandHandler: CommandHandler,
    private val messageHandler: MessageHandler,
    private val callbackQueryHandler: CallbackQueryHandler,
    private val accountService: AccountService
) : UpdateHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

    override fun onUpdateReceived(update: Update, sender: AbsSender) {

        when {
            update.hasCallbackQuery() -> handleCallbackQuery(update.callbackQuery, sender)
            update.hasMessage() -> checkAuthorities(getMessage(update), sender)
        }

    }

    private fun checkAuthorities(message: Message, sender: AbsSender){
        if (accountFindService.isExistsByChatId(message.chatId.toString())){
            handle(message, sender)
        } else {
            authoritiesFailed(message, sender)
        }
    }

    private fun handle(message: Message, sender: AbsSender){
        if (messageHasCommand(message)){
            handleCommand(message, sender)
        } else {
            handleMessage(message, sender)
        }
    }

    private fun authoritiesFailed(message: Message, sender: AbsSender){
        log.warn(
            "Non registered user tried to use bot - " +
                    "chat_id: ${message.chatId}, " +
                    "user: ${getSenderUsername(message)}, ${getSenderFirstName(message)}, " +
                    "message text: ${getMessageTextOrBlank(message)}"
        )
        val messageToSend = sendMessageFactory.getSendMessage(
            message.chatId.toString(),
            "Вы не зарегистрированы"
        )
        telegramMessageSenderService.send(sender, messageToSend, null)
    }

    private fun handleCommand(message: Message, sender: AbsSender){
        // user will now talk directly to bot
        accountService.emptyCurrentIssue(
            accountFindService.findByChatId(message.chatId.toString()).id
        )
        commandHandler.handle(message, sender)
    }

    private fun handleMessage(message: Message, sender: AbsSender){
        messageHandler.handleMessage(message, sender)
    }

    private fun handleCallbackQuery(callbackQuery: CallbackQuery, sender: AbsSender){
        callbackQueryHandler.handle(callbackQuery, sender)
    }

}
