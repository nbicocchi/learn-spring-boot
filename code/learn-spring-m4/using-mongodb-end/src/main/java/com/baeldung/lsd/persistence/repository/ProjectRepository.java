package com.baeldung.lsd.persistence.repository;

import com.baeldung.lsd.persistence.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String> {

    Optional<Project> findProjectByCode(String code);
}
