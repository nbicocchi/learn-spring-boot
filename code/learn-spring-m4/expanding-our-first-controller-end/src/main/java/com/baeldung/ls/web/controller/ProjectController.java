package com.baeldung.ls.web.controller;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Iterable<Project> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/find")
    public Iterable<Project> findProjects(@RequestParam("name") String name) {
        return projectService.findByName(name);
    }

    @GetMapping(value = "/{id}")
    public Project findOne(@PathVariable Long id) {
        Project project = projectService.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return project;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody(required = true) Project project) {
        return projectService.save(project);
    }

    @PutMapping(value = "/{id}")
    public Project update(@PathVariable Long id, @RequestBody(required = true) Project project) {
        return projectService.updateById(id, project);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        projectService.deleteById(id);
    }
}
