package com.baeldung.ls.web.controller;

import com.baeldung.ls.mapper.ProjectMapper;
import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto create(@RequestBody ProjectDto newProject) {
        Project entity = mapper.projectDTOToProject(newProject);
        return mapper.projectToProjectDTO(projectService.save(entity));
    }

    @GetMapping
    public Collection<ProjectDto> findAll() {
        Iterable<Project> allProjects = this.projectService.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        allProjects.forEach(p -> projectDtos.add(mapper.projectToProjectDTO(p)));
        return projectDtos;
    }

    @PutMapping("/{id}")
    public ProjectDto updateProject(@PathVariable("id") Long id, @RequestBody ProjectDto updatedProject) {
        Project entity = mapper.projectDTOToProject(updatedProject);
        return mapper.projectToProjectDTO(projectService.save(entity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("id") Long id) {
        projectService.deleteById(id);
    }
}