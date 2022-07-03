package com.tanto.wingman.repos;

import com.tanto.wingman.data.entities.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GeneralAccountTests {

    @Autowired
    AccountRepository repository;

    @Test
    void adminExistsAndHasLoginAdminTest(){
        Account admin = repository.getAdmin();
        Assertions.assertNotNull(admin);
        Assertions.assertEquals("admin", admin.getLogin());
    }

}
