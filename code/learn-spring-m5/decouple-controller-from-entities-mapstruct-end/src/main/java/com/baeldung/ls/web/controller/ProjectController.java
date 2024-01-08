package com.baeldung.ls.web.controller;

import com.baeldung.ls.mapper.ProjectMapper;
import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {
    private IProjectService projectService;
    private ProjectMapper mapper;

    public ProjectController(IProjectService projectService, ProjectMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{id}")
    public ProjectDto findOne(@PathVariable Long id) {
        Project entity = projectService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return mapper.projectToProjectDTO(entity);
    }

    @PostMapping
    public void create(@RequestBody ProjectDto newProject) {
        Project entity = mapper.projectDTOToProject(newProject);
        this.projectService.save(entity);
    }
}
