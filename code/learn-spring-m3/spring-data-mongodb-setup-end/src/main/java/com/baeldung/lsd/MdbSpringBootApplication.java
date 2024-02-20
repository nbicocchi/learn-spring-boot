package com.baeldung.lsd;

import com.baeldung.lsd.persistence.model.Project;
import com.baeldung.lsd.persistence.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{
	private static Logger log = LoggerFactory.getLogger(MdbSpringBootApplication.class);
	ProjectRepository projectRepository;

	public MdbSpringBootApplication(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
	
	public void run(String... args) {
		List<Project> projects;
		projectRepository.deleteAll();

		projectRepository.save(new Project("P01", "Project 01", "About Project 01"));
		projectRepository.save(new Project("P02", "Project 02", "About Project 02"));
		projectRepository.save(new Project("P03", "Project 03", "About Project 03"));

		projects = projectRepository.findAll();
		projects.forEach(item -> log.info(item.toString()));

		Long count = projectRepository.count();
		log.info(String.format("%d\n", count));

		Optional<Project> projectOptional = projectRepository.findProjectByCode("P01");
        projectOptional.ifPresent(project -> log.info(project.toString()));

		Project saved = projectRepository.save(new Project("P04", "Project 04", "About Project 04"));
		saved.setName("Test name");
		projectRepository.save(saved);

		projects = projectRepository.findAll();
		projects.forEach(item -> log.info(item.toString()));

	}
}

