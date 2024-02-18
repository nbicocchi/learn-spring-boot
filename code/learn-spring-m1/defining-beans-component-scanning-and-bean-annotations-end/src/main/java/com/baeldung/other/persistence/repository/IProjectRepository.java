package com.baeldung.other.persistence.repository;

import java.util.Collection;
import java.util.Optional;

import com.baeldung.other.persistence.model.Project;

public interface IProjectRepository {

    Optional<Project> findById(Long id);

    Collection<Project> findAll();

    Project save(Project project);
}
