package com.tanto.wingman.services.wingman.implementations

import com.tanto.wingman.data.entities.Message
import com.tanto.wingman.services.SendIssueMessageService
import com.tanto.wingman.services.TelegramMessageService
import com.tanto.wingman.services.data.MessageService
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.find.IssueFindService
import com.tanto.wingman.services.wingman.WingmanService
import com.tanto.wingman.utils.TelegramMessagesUtils
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message as TgMessage
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class WingmanServiceImpl(
    private val issueFindService: IssueFindService,
    private val accountFindService: AccountFindService,
    private val messageService: MessageService,
    private val telegramMessageService: TelegramMessageService,
    private val sendIssueMessageService: SendIssueMessageService
) : WingmanService {

    override fun getStandardResponse(): String {
        return TelegramMessagesUtils.getZzzEmoji()
    }

    override fun handleMessage(message: TgMessage, sender: AbsSender) {

        val originalMessageChatId = telegramMessageService.getChatId(message)
        val senderAccount = accountFindService.findByChatId(originalMessageChatId)
        val issue = issueFindService.findCurrentByAccountId(senderAccount.id)
        val savedMessage = messageService.saveMessageFromTelegram(message, issue.id)

        if (messageService.isSentFromClient(savedMessage.id)){
            sentFromClient(savedMessage, sender)
        } else {
            sentFromEmployee(savedMessage, sender)
        }

    }

    private fun sentFromClient(message: Message, sender: AbsSender){
        sendIssueMessageService.sendToEmployee(message, sender)
    }

    private fun sentFromEmployee(message: Message, sender: AbsSender){
        sendIssueMessageService.sendToClient(message, sender)
    }

}
