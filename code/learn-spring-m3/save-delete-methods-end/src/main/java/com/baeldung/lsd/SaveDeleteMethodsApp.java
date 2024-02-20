package com.baeldung.lsd;

import com.baeldung.lsd.persistence.model.Project;
import com.baeldung.lsd.persistence.model.Task;
import com.baeldung.lsd.persistence.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SaveDeleteMethodsApp implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(SaveDeleteMethodsApp.class);

    private final ProjectRepository projectRepository;

    public SaveDeleteMethodsApp(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public static void main(final String... args) {
        SpringApplication.run(SaveDeleteMethodsApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        saveMethods();
        //deleteMethods();
    }

    private void saveMethods() {
        // save new entity
        Project newProject = new Project("NEW1", "new project", "new project description");
        LOG.info("Project id before persisting: {}", newProject.getId());
        projectRepository.save(newProject);
        LOG.info("Project id after persisting: {}", newProject.getId());

        // update entity (has id)
        newProject.setName("updated name");
        Set<Task> newProjectTasks = Set.of(new Task("task name", "task description", LocalDate.of(2025, 1, 1), newProject));
        newProject.setTasks(newProjectTasks);
        newProject = projectRepository.save(newProject);
        LOG.info("Child Task after updating: {}", newProject.getTasks());

        // update existing projects and save new ones
        newProject.setName("updated again");

        Project p1 = projectRepository.findById(1L).orElseThrow(RuntimeException::new);
        Set<Task> differentTasks = Set.of(new Task("different task", "different description", LocalDate.of(2025, 1, 1), p1));
        p1.setTasks(differentTasks);

        Project newProject2 = new Project("NEW2", "another project", "another project description");
        Iterable<Project> severalProjects = Arrays.asList(newProject, p1, newProject2);
        projectRepository.saveAll(severalProjects);
        LOG.info("Project after persisting: {}", newProject);
        LOG.info("Project after persisting: {}", p1);
        LOG.info("Project after persisting: {}", newProject2);

        // save multiple entities acts as a single transaction
        Project newProject3 = new Project("NEW3", "duplicate code!", "project with constraint violation");
        Project newProject4 = new Project("NEW3", "duplicate code!", "project with constraint violation");
        try {
            projectRepository.saveAll(Arrays.asList(newProject3, newProject4));
        } catch (Exception ex) {
            LOG.info("error saving/updating multiple entities");
        }
    }

    private void deleteMethods() {
        // delete using reference
        Project toDelete = projectRepository.findById(1L).orElseThrow(RuntimeException::new);
        projectRepository.delete(toDelete);

        // delete using id
        projectRepository.deleteById(2L);

        // delete using ids
        Iterable<Project> projectsToDelete = projectRepository.findAllById(List.of(3L, 5L));
        projectRepository.deleteAll(projectsToDelete);

        // delete using custom query and with count
        Long deleteCount = projectRepository.deleteByNameContaining("Project 2");
        LOG.info("Number of removed projects:\n{}", deleteCount);

        // delete using custom query
        projectRepository.deleteByNameContaining("updated");
    }
}
