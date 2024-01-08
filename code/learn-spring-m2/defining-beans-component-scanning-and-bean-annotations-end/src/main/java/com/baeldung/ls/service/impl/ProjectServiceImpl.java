package com.baeldung.ls.service.impl;

import com.baeldung.ls.service.IProjectService;
import com.baeldung.other.persistence.model.Project;
import com.baeldung.other.persistence.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Lazy
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    public ProjectServiceImpl(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

}
