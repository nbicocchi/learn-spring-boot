package com.baeldung.other.persistence.repository;

import com.baeldung.other.persistence.model.Project;

import java.util.Optional;

public interface IProjectRepository {

    Optional<Project> findById(Long id);

    Project save(Project project);
}
