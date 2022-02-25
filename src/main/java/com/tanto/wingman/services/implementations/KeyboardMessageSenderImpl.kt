package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.Command
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.repos.MessageRepository
import com.tanto.wingman.services.KeyboardMessageSender
import com.tanto.wingman.services.SendMessageFactory
import com.tanto.wingman.services.SendMessageWithKeyboardFactory
import com.tanto.wingman.services.TelegramMessageSenderService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.utils.KeyboardUtils.getInlineKeyboardButtonWithLog
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.*

@Service
class KeyboardMessageSenderImpl(
    private val sendMessageFactory: SendMessageFactory,
    private val issueFindService: IssueFindService,
    private val messageRepository: MessageRepository,
    private val sendMessageWithKeyboardFactory: SendMessageWithKeyboardFactory,
    private val senderService: TelegramMessageSenderService,
    private val accountFindService: AccountFindService
) : KeyboardMessageSender {

    override fun sendIssuesListToClientChat(chatId: String, sender: AbsSender) {

        val account = accountFindService.findByChatId(chatId)
        sendIssuesListToClientChat(account, sender)

    }

    override fun sendIssuesListToClientChat(account: Account, sender: AbsSender){

        val issues = issueFindService.findByClientId(account.id)
        val buttons = issues.map {
            getButtonList("${it.code} (${messageRepository.countByIssueIdAndReadByReceiverIsFalse(it.id)})", it.code, Command.ISSUES)
        }
        sendMessageWithKeyboard("Выберите обращение из списка", account.chatId, buttons, sender)

    }

    override fun sendIssuesListToEmployeeChat(chatId: String, sender: AbsSender) {

        val account = accountFindService.findByChatId(chatId)
        sendIssuesListToEmployeeChat(account, sender)

    }

    override fun sendIssuesListToEmployeeChat(account: Account, sender: AbsSender){

        val issues = issueFindService.findByEmployeeId(account.id)
        val buttons = issues.map {
            getButtonList("${it.code} (${messageRepository.countByIssueIdAndReadByReceiverIsFalse(it.id)})", it.code, Command.ISSUES)
        }
        sendMessageWithKeyboard("Выберите обращение из списка", account.chatId, buttons, sender)

    }

    private fun sendMessageWithKeyboard(
        messageText: String,
        chatId: String,
        buttons: List<List<InlineKeyboardButton>>,
        sender: AbsSender
    ) {
        val messageToSend = sendMessageFactory.getSendMessage(chatId, messageText)
        val messageToSendWithKeyboard = sendMessageWithKeyboardFactory.add(messageToSend, buttons)
        senderService.send(sender, messageToSendWithKeyboard, null)
    }

    private fun getButtonList(text: String, callbackText: String, command: Command): List<InlineKeyboardButton>{
        return listOf(
            getInlineKeyboardButtonWithLog(text, callbackText, command)
        )
    }

}
