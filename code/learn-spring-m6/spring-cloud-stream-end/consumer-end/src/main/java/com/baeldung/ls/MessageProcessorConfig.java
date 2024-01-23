package com.baeldung.ls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class MessageProcessorConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProcessorConfig.class);

    @Bean
    public Function<String, String> toLowerCase() {
            return event -> event.toLowerCase();
    }

    @Bean
    public Function<String, String> toUpperCase() {
        return event -> event.toUpperCase();
    }

    @Bean
    public Consumer<String> consumerPlain() {
        return event -> LOG.info(event);
    }

    @Bean
    public Consumer<String> consumerFancy() {
        return event -> LOG.info(String.format("--> %s", event));
    }
}
