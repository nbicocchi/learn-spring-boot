package com.baeldung.ls;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LsApp {

    IProjectRepository repository;

    public LsApp(IProjectRepository repository) {
        this.repository = repository;
    }

    public static void main(final String... args) {
        SpringApplication.run(LsApp.class, args);
    }
}
