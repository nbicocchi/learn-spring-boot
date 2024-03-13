package com.baeldung.ls;

import com.baeldung.other.persistence.model.Project;
import com.baeldung.other.persistence.repository.IProjectRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.random.RandomGenerator;

@SpringBootApplication
@ComponentScan(basePackages = "com.baeldung")
public class LsApp implements ApplicationRunner {
    public static final RandomGenerator RND = RandomGenerator.getDefault();

    IProjectRepository projectRepository;

    public LsApp(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public static void main(final String... args) {
        SpringApplication.run(LsApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        projectRepository.save(new Project("P1", LocalDate.now()));
        projectRepository.save(new Project("P2", LocalDate.now()));
        projectRepository.save(new Project("P3", LocalDate.now()));
    }
}
