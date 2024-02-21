package com.baeldung.ls.service;

import com.baeldung.ls.persistence.model.Project;

import java.util.Collection;
import java.util.Optional;

public interface IProjectService {

    Iterable<Project> findAll();
    Iterable<Project> findByName(String name);
    Optional<Project> findById(Long id);
    Project save(Project project);
    Project updateById(Long id, Project project);
    void deleteById(Long id);
}
