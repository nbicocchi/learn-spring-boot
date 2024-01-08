package com.baeldung.ls;

import java.time.LocalDate;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;

@SpringBootApplication
public class LsApp {

    private static final Logger LOG = LoggerFactory.getLogger(LsApp.class);

    @Autowired
    private IProjectService projectService;

    @Value("${additional.info}")
    private String additional;

    public static void main(final String... args) {
        SpringApplication.run(LsApp.class, args);
        for (String arg : args) {
            System.out.println(arg);
        }
    }

    @PostConstruct
    public void postConstruct() {
        LOG.info("Additional Property {}", additional);
        Project project = new Project("My First Project", LocalDate.now());
        projectService.save(project);
    }
}
