package com.baeldung.ls.service;

import com.baeldung.ls.persistence.model.Task;

import java.util.Optional;

public interface ITaskService {
    Optional<Task> findById(Long id);

    Iterable<Task> findAll();

    Task save(Task project);

    void deleteById(Long id);
}