package com.tanto.wingman.services.implementations

import com.tanto.wingman.data.Command
import com.tanto.wingman.data.entities.Account
import com.tanto.wingman.services.CommandHandler
import com.tanto.wingman.services.CommandService
import com.tanto.wingman.services.KeyboardMessageSender
import com.tanto.wingman.services.data.find.AccountFindService
import com.tanto.wingman.services.data.implementations.IssueFactoryCacheServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.api.objects.Message as TgMessage

// todo extract business logic to CommandService
@Service
class CommandHandlerImpl(
    private val commandService: CommandService,
    private val accountFindService: AccountFindService,
    private val keyboardMessageSender: KeyboardMessageSender,
    private val issueFactoryCacheServiceImpl: IssueFactoryCacheServiceImpl
) : CommandHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.name)

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
            Command.ISSUES -> clientIssues(senderAccount, sender)
            Command.START -> start()
            Command.ISSUE -> newIssueFromClient(senderAccount, sender)
        }
    }

    private fun sentFromEmployee(senderAccount: Account, tgMessage: TgMessage, sender: AbsSender){
        when (commandService.getCommandFromMessage(tgMessage)) {
            Command.ISSUES -> employeeIssues(senderAccount, sender)
            Command.START -> start()
            Command.ISSUE -> {
                log.info("Employee ${senderAccount.name} ${senderAccount.surname} tried to create issue")
            }
//            else -> {
//                log.info("Employee ${senderAccount.name} ${senderAccount.surname} used command " +
//                        "${commandService.getCommandFromMessage(tgMessage)} that is not currently supported")
//            }
        }
    }

    private fun clientIssues(senderAccount: Account, sender: AbsSender){
        keyboardMessageSender.sendIssuesListToClientChat(senderAccount, sender)
    }

    private fun employeeIssues(senderAccount: Account, sender: AbsSender){
        keyboardMessageSender.sendIssuesListToEmployeeChat(senderAccount, sender)
    }

    private fun newIssueFromClient(senderAccount: Account, sender: AbsSender){
        issueFactoryCacheServiceImpl.putAccountToCacheWithCommand(senderAccount, Command.ISSUE)
        keyboardMessageSender.sendChooseDepartmentDialogToChat(senderAccount, sender)
    }

    private fun start(){
        log.info("someone tried to use /start")
    }

}
