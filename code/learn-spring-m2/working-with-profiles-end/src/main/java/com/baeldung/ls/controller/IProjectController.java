package com.baeldung.ls.controller;

import com.baeldung.ls.persistence.model.Project;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping(value = "/projects")
public interface IProjectController {
    @GetMapping(value = "/{id}")
    Project findOne(@PathVariable Long id);

    @GetMapping
    Collection<Project> findAll();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Project create(@RequestBody Project project);
}
