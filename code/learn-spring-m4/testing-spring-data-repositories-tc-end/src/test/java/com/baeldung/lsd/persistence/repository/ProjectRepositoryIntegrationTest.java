package com.baeldung.lsd.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.lsd.persistence.model.Project;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProjectRepositoryIntegrationTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    ProjectRepository projectRepository;

    @Test
    @Transactional
    void givenNewProject_whenSave_thenSuccess() {
        Project newProject = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");

        Project saved = projectRepository.save(newProject);
        Optional<Project> fetchedProject = projectRepository.findById(saved.getId());

        assertThat(fetchedProject).contains(newProject);
    }

    @Test
    @Transactional
    void givenProjectCreated_whenUpdate_thenSuccess() {
        Project newProject = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");
        Project saved = projectRepository.save(newProject);

        saved.setName("New Project 001");
        Project updated = projectRepository.save(saved);

        Optional<Project> fetchedProject = projectRepository.findById(updated.getId());

        assertThat(fetchedProject.isPresent()).isTrue();
        assertThat(fetchedProject.get().getCode()).isEqualTo(newProject.getCode());
        assertThat(fetchedProject.get().getName()).isEqualTo("New Project 001");
        assertThat(fetchedProject.get().getDescription()).isEqualTo(newProject.getDescription());
    }

    @Test
    @Transactional
    void givenProjectCreated_whenFindByNameContaining_thenSuccess() {
        Project newProject1 = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");
        Project newProject2 = new Project("PTEST-2", "Test Project 2", "Description for project PTEST-2");
        projectRepository.save(newProject1);
        projectRepository.save(newProject2);

        Iterable<Project> projects = projectRepository.findByNameContaining("Test");
        assertThat(projects).contains(newProject1, newProject2);
    }

    @Test
    @Transactional
    void givenProjectCreated_whenDelete_thenSuccess() {
        Project newProject = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");
        Project saved = projectRepository.save(newProject);
        projectRepository.delete(newProject);

        Optional<Project> fetchedProject = projectRepository.findById(saved.getId());
        assertThat(fetchedProject.isPresent()).isFalse();
    }


}