package com.baeldung.ls.service;

import com.baeldung.ls.persistence.model.Project;

import java.util.Optional;

public interface IProjectService {
    Optional<Project> findById(Long id);

    Iterable<Project> findAll();

    Iterable<Project> findByName(String name);

    Project save(Project project);

    void deleteById(Long id);
}
