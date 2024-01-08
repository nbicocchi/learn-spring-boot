package com.baeldung.ls;

import com.baeldung.ls.mapper.ProjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
public class SpringContextIntegrationTest {

    @Test
    public void whenContextIsLoaded_thenNoExceptions() {
    }
}