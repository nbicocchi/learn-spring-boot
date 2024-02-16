package com.baeldung.ls.persistence.repository.impl;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements IProjectRepository {

    private final List<Project> projects = new ArrayList<>();

    @Override
    public Optional<Project> findById(Long id) {
        return projects.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Collection<Project> findAll() {
        return projects;
    }

    @Override
    public Project save(Project project) {
        Optional<Project> existingProject = findById(project.getId());
        if (existingProject.isPresent()) {
            projects.remove(existingProject);
        }
        Project newProject = new Project(project);
        projects.add(newProject);
        return newProject;
    }
}
