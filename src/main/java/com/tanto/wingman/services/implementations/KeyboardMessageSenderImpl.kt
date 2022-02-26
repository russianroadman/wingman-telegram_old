package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.CallbackCode
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.data.entities.Issue
import com.tanto.wingman.repos.MessageRepository
import com.tanto.wingman.services.KeyboardMessageSender
import com.tanto.wingman.services.SendMessageFactory
import com.tanto.wingman.services.TelegramMessageSenderService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.DepartmentFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.utils.AppConstants.CHOOSE_ISSUE
import com.tanto.wingman.utils.KeyboardUtils.getInlineKeyboardButtonWithLog
import com.tanto.wingman.utils.TelegramMessagesUtils.addKeyboard
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class KeyboardMessageSenderImpl(
    private val sendMessageFactory: SendMessageFactory,
    private val issueFindService: IssueFindService,
    private val messageRepository: MessageRepository, // todo move to find service
    private val senderService: TelegramMessageSenderService,
    private val accountFindService: AccountFindService,
    private val departmentFindService: DepartmentFindService
) : KeyboardMessageSender {

    override fun sendIssuesListToClientChat(clientChatId: String, sender: AbsSender) {

        val account = accountFindService.findByChatId(clientChatId)
        sendIssuesListToClientChat(account, sender)

    }

    override fun sendIssuesListToClientChat(client: Account, sender: AbsSender){

        val issues = issueFindService.findByClientId(client.id)
        val buttons = issues.map {
            getButtonList("${it.code}: ${getUnreadMessagesCount(it, client)}", it.code, CallbackCode.GET_ISSUE_BY_CODE)
        }
        sendMessageWithKeyboard(CHOOSE_ISSUE, client.chatId, buttons, sender)

    }

    override fun sendIssuesListToEmployeeChat(employeeChatId: String, sender: AbsSender) {

        val account = accountFindService.findByChatId(employeeChatId)
        sendIssuesListToEmployeeChat(account, sender)

    }

    override fun sendIssuesListToEmployeeChat(employee: Account, sender: AbsSender){

        val issues = issueFindService.findByEmployeeId(employee.id)
        val buttons = issues.map {
            getButtonList("${it.code}: ${getUnreadMessagesCount(it, employee)}", it.code, CallbackCode.GET_ISSUE_BY_CODE)
        }
        sendMessageWithKeyboard(CHOOSE_ISSUE, employee.chatId, buttons, sender)

    }

    override fun sendChooseDepartmentDialogToChat(chatId: String, sender: AbsSender) {

        val account = accountFindService.findByChatId(chatId)
        sendChooseDepartmentDialogToChat(account, sender)

    }

    override fun sendChooseDepartmentDialogToChat(account: Account, sender: AbsSender) {

        val departments = departmentFindService.findByAccountId(account.id)
        val buttons = departments.map {
            getButtonList(it.name, it.name, CallbackCode.CHOOSE_DEPARTMENT_FOR_NEW_ISSUE)
        }
        sendMessageWithKeyboard("Выберите отдел:", account.chatId, buttons, sender)

    }

    private fun sendMessageWithKeyboard(
        messageText: String,
        chatId: String,
        buttons: List<List<InlineKeyboardButton>>,
        sender: AbsSender
    ) {
        val messageToSend = sendMessageFactory.getSendMessage(chatId, messageText)
        val messageToSendWithKeyboard = addKeyboard(messageToSend, buttons)
        senderService.send(sender, messageToSendWithKeyboard, null)
    }

    private fun getButtonList(text: String, callbackText: String, callbackCode: CallbackCode): List<InlineKeyboardButton>{
        return listOf(
            getInlineKeyboardButtonWithLog(text, callbackText, callbackCode)
        )
    }

    private fun getUnreadMessagesCount(issue: Issue, account: Account): Long{
        return messageRepository.countUnread(issue.id, account.id)
    }

}
