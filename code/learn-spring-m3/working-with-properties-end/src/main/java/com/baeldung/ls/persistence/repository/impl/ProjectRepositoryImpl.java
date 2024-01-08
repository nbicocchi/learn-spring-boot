package com.baeldung.ls.persistence.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;

@Repository
public class ProjectRepositoryImpl implements IProjectRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectRepositoryImpl.class);

    @Value("${project.prefix}")
    private String prefix;

    @Value("${project.suffix}")
    private Integer suffix;

    private List<Project> projects = new ArrayList<>();

    @Override
    public Optional<Project> findById(Long id) {
        return projects.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }

    @Override
    public Project save(Project project) {
        Project existingProject = findById(project.getId()).orElse(null);
        updateInternalId(project);

        if (existingProject == null) {
            projects.add(project);
        } else {
            projects.remove(existingProject);
            Project newProject = new Project(project);
            projects.add(newProject);
        }
        return project;
    }

    private void updateInternalId(Project project) {
        LOG.info("Prepending Prefix " + prefix);
        LOG.info("Appending Suffix " + suffix);

        project.setInternalId(prefix + "-" + project.getId() + "-" + suffix);

        LOG.info("Generated internal id " + project.getInternalId());
    }

}
