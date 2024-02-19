package com.baeldung.ls.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(ObjectMapper.class)
public class JacksonAutoConfiguration {

    public JacksonAutoConfiguration() {
    }
}
