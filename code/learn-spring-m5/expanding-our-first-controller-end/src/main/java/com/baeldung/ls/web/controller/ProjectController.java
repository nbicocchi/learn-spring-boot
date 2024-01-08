package com.baeldung.ls.web.controller;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{id}")
    public Project findOne(@PathVariable Long id) {
        return projectService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public void create(@RequestBody Project newProject) {
        this.projectService.save(newProject);
    }
}
