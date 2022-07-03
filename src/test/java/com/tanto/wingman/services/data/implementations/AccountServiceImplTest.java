package com.tanto.wingman.services.data.implementations;

import com.tanto.wingman.data.Bot;
import com.tanto.wingman.data.entities.Account;
import com.tanto.wingman.repos.AccountRepository;
import com.tanto.wingman.services.UpdateHandler;
import com.tanto.wingman.services.data.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    UpdateHandler updateHandler;

    @MockBean
    AccountRepository accountRepository;

    @Test
    void informAboutNewMessagesWhenThereAreMessages() throws TelegramApiException {

        Bot mockBot = mock(Bot.class);

        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setChatId("some_chat_id");
        account.setLogin("some_login");
        account.setPasswordHash("some_pass_hash");
        account.setName("Name");
        account.setSurname("Surname");

        when(accountRepository.countUnread(account.getId())).thenReturn(1L);

        accountService.informAboutNewMessages(account, mockBot);

        Mockito.verify(mockBot, Mockito.times(1)).execute(any(SendMessage.class));

    }

    @Test
    void informAboutNewMessagesWhenThereAreNoMessages() throws TelegramApiException {

        Bot mockBot = mock(Bot.class);

        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setChatId("some_chat_id");
        account.setLogin("some_login");
        account.setPasswordHash("some_pass_hash");
        account.setName("Name");
        account.setSurname("Surname");

        when(accountRepository.countUnread(account.getId())).thenReturn(0L);
        when(mockBot.execute(any(SendMessage.class))).thenReturn(new Message());

        accountService.informAboutNewMessages(account, mockBot);

        Mockito.verify(mockBot, Mockito.times(0)).execute(any(SendMessage.class));

    }

}
