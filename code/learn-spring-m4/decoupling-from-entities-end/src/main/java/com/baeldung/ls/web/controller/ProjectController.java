package com.baeldung.ls.web.controller;

import com.baeldung.ls.mapper.ProjectMapper;
import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private IProjectService projectService;
    private ProjectMapper projectMapper;

    public ProjectController(IProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    public Iterable<ProjectDto> findAll() {
        Iterable<Project> projects = projectService.findAll();
        return StreamSupport.stream(projects.spliterator(), false)
                .map(p -> projectMapper.projectToProjectDTO(p))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ProjectDto findOne(@PathVariable Long id) {
        Project project = projectService.findById(id);
        return projectMapper.projectToProjectDTO(project);
    }

    @PostMapping
    public ProjectDto create(@RequestBody ProjectDto projectDto) {
        Project project = projectMapper.projectDTOToProject(projectDto);
        Project saved = projectService.save(project);
        return projectMapper.projectToProjectDTO(saved);
    }

    @PutMapping(value = "/{id}")
    public ProjectDto update(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        Project project = projectMapper.projectDTOToProject(projectDto);
        Project saved = projectService.updateById(id, project);
        return projectMapper.projectToProjectDTO(saved);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        projectService.deleteById(id);
    }
}
