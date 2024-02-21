package com.baeldung.ls.service.impl;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements IProjectService {

    private final IProjectRepository projectRepository;

    public ProjectServiceImpl(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project %d not found", id)));
        return project;
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateById(Long id, Project updatedProject) {
        Project foundProject = findById(id);
        updatedProject.setId(foundProject.getId());
        return projectRepository.save(updatedProject);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        projectRepository.deleteById(id);
    }
}
