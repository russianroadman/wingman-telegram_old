package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.Command
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.services.CommandHandler
import com.tanto.wingman.services.CommandService
import com.tanto.wingman.services.SendMessageFactory
import com.tanto.wingman.services.TelegramMessageSenderService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message as TgMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class CommandHandlerImpl(
    private val commandService: CommandService,
    private val accountFindService: AccountFindService,
    private val issueFindService: IssueFindService,
    private val sendMessageFactory: SendMessageFactory,
    private val keyboardFactoryImpl: SendMessageWithKeyboardFactoryImpl,
    private val senderService: TelegramMessageSenderService
) : CommandHandler {

    override fun handle(message: TgMessage, sender: AbsSender) {

        val originalMessageChatId = message.chatId.toString()
        val senderAccount = accountFindService.findByChatId(originalMessageChatId)

        if (senderAccount.client){
            sentFromClient(senderAccount, message, sender)
        } else {
            sentFromEmployee(senderAccount, message, sender)
        }

    }

    private fun sentFromClient(senderAccount: Account, tgMessage: TgMessage, sender: AbsSender){
        when (commandService.getCommandFromMessage(tgMessage)) {
            Command.ISSUES -> clientIssues(senderAccount, tgMessage, sender)
            Command.START -> start()
            else -> {}
        }
    }

    private fun sentFromEmployee(senderAccount: Account, tgMessage: TgMessage, sender: AbsSender){
        when (commandService.getCommandFromMessage(tgMessage)) {
            Command.ISSUES -> employeeIssues(senderAccount, tgMessage, sender)
            Command.START -> start()
            else -> {}
        }
    }

    private fun clientIssues(senderAccount: Account, tgMessage: TgMessage, sender: AbsSender){

        val messageToSend = sendMessageFactory.getSendMessage(tgMessage.chatId.toString(), "Выберите обращение из списка")
        val issueCodes = issueFindService.findByClientId(senderAccount.id).map { it.code }

        val buttons = listOf(
            issueCodes.map {
                InlineKeyboardButton
                    .builder()
                    .text(it)
                    .callbackData("${Command.ISSUES}:$it")
                    .build()
            }
        )
        val messageToSendWithKeyboard = keyboardFactoryImpl.add(messageToSend, buttons)
        senderService.send(sender, messageToSendWithKeyboard)

    }

    private fun employeeIssues(senderAccount: Account, tgMessage: TgMessage, sender: AbsSender){

        val messageToSend = sendMessageFactory.getSendMessage(tgMessage.chatId.toString(), "Выберите обращение из списка")
        val issueCodes = issueFindService.findByEmployeeId(senderAccount.id).map { it.code }

        val buttons = listOf(
            issueCodes.map {
                InlineKeyboardButton
                    .builder()
                    .text(it)
                    .callbackData("${Command.ISSUES}:$it")
                    .build()
            }
        )
        val messageToSendWithKeyboard = keyboardFactoryImpl.add(messageToSend, buttons)
        senderService.send(sender, messageToSendWithKeyboard)

    }

    private fun start(){
        TODO()
    }

}
