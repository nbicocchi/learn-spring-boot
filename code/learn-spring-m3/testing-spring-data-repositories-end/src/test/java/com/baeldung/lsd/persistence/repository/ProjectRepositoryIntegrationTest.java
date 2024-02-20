package com.baeldung.lsd.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.baeldung.lsd.persistence.model.Project;

@DataJpaTest
class ProjectRepositoryIntegrationTest {

    @Autowired
    ProjectRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenNewProject_whenSave_thenSuccess() {
        Project newProject = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");

        Project saved = repository.save(newProject);
        Optional<Project> fetchedProject = repository.findById(saved.getId());

        assertThat(fetchedProject).contains(newProject);
    }

    @Test
    void givenProjectCreated_whenUpdate_thenSuccess() {
        Project newProject = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");
        Project saved = repository.save(newProject);

        saved.setName("New Project 001");
        Project updated = repository.save(saved);

        Optional<Project> fetchedProject = repository.findById(updated.getId());

        assertThat(fetchedProject.isPresent()).isTrue();
        assertThat(fetchedProject.get().getCode()).isEqualTo(newProject.getCode());
        assertThat(fetchedProject.get().getName()).isEqualTo("New Project 001");
        assertThat(fetchedProject.get().getDescription()).isEqualTo(newProject.getDescription());
    }

    @Test
    void givenProjectCreated_whenFindByNameContaining_thenSuccess() {
        Project newProject1 = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");
        Project newProject2 = new Project("PTEST-2", "Test Project 2", "Description for project PTEST-2");
        repository.save(newProject1);
        repository.save(newProject2);

        Iterable<Project> projects = repository.findByNameContaining("Test");
        assertThat(projects).contains(newProject1, newProject2);
    }

    @Test
    void givenProjectCreated_whenDelete_thenSuccess() {
        Project newProject = new Project("PTEST-1", "Test Project 1", "Description for project PTEST-1");
        Project saved = repository.save(newProject);
        repository.delete(newProject);

        Optional<Project> fetchedProject = repository.findById(saved.getId());
        assertThat(fetchedProject.isPresent()).isFalse();
    }

}