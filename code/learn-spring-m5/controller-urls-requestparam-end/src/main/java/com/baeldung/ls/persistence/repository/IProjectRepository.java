package com.baeldung.ls.persistence.repository;

import com.baeldung.ls.persistence.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface IProjectRepository extends CrudRepository<Project, Long> {
    Iterable<Project> findByNameContaining(String name);
}