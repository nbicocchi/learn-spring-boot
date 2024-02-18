package com.baeldung.ls.service.impl;

import java.util.Optional;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.repository.IProjectRepository;
import com.baeldung.ls.service.IProjectService;

@Service
public class ProjectServiceImplFieldInjection implements IProjectService {

    private static Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private IProjectRepository projectRepository;

    @PostConstruct
    public void post() {
        log.info("Starting " + getClass().getName() + "...");
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
