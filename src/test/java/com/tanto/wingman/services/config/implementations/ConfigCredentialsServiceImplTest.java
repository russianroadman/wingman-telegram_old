package com.tanto.wingman.services.config.implementations;

import com.tanto.wingman.data.Command;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigCredentialsServiceImplTest {

    @Test
    public void name() {
        Arrays.stream(Command.values()).forEach(e -> System.out.println(e.toString()));
    }
}
