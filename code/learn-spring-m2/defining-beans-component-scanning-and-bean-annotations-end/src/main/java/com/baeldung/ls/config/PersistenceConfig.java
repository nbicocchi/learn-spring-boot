package com.baeldung.ls.config;

import com.baeldung.other.persistence.repository.impl.ProjectRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {
    @Bean
    public ProjectRepositoryImpl projectRepository() {
        return new ProjectRepositoryImpl();
    }
}
