package com.baeldung.ls.service;

import com.baeldung.ls.persistence.model.Task;

import java.util.Optional;

public interface ITaskService {
    Optional<Task> findById(Long id);

    Task save(Task project);
}