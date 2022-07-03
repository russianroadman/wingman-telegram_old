package com.tanto.wingman.repos;

import com.tanto.wingman.data.IssueStatus;
import com.tanto.wingman.data.entities.Account;
import com.tanto.wingman.data.entities.Department;
import com.tanto.wingman.data.entities.Issue;
import com.tanto.wingman.data.entities.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void countUnreadWhenThereAreUnread(){

        Account account = createUserWithUnreadMessages();
        assertTrue(accountRepository.countUnread(account.getId()) > 0);

    }

    private Account createUserWithUnreadMessages(){

        Account account = new Account();
        account.setChatId("some_chat_id");
        account.setLogin("some_login");
        account.setPasswordHash("some_pass_hash");
        account.setName("Name");
        account.setSurname("Surname");

        Account savedAccount = accountRepository.save(account);

        Account anotherAccount = new Account();
        anotherAccount.setChatId("some_chat_id1");
        anotherAccount.setLogin("some_login1");
        anotherAccount.setPasswordHash("some_pass_hash1");
        anotherAccount.setName("Name1");
        anotherAccount.setSurname("Surname1");

        Account savedAnotherAccount = accountRepository.save(anotherAccount);

        Department department = new Department();
        department.setName("department");

        Department savedDepartment = departmentRepository.save(department);

        Issue issue = new Issue();
        issue.setCode("123");
        issue.setCreatedAt(new Date());
        issue.setStatus(IssueStatus.IN_PROGRESS);
        issue.setClient(savedAccount);
        issue.setEmployee(savedAnotherAccount);
        issue.setDepartment(savedDepartment);

        Issue savedIssue = issueRepository.save(issue);

        Message message = new Message();
        message.setCreatedBy(savedAnotherAccount);
        message.setCreatedAt(new Date());
        message.setChatId("some_chat_id");
        message.setTelegramMessageId(1);
        message.setReadByReceiver(false);
        message.setIssue(savedIssue);

        messageRepository.save(message);

        return savedAccount;

    }

}
