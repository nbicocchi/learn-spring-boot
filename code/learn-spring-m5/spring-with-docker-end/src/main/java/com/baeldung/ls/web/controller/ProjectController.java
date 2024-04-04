package com.baeldung.ls.web.controller;

import com.baeldung.ls.mapper.ProjectMapper;
import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.dto.ProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private final IProjectService projectService;
    private final ProjectMapper mapper;

    public ProjectController(IProjectService projectService, ProjectMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @Operation(
            summary = "${api.project.get-project.summary}",
            description = "${api.project.get-project.description}"
    )
    @GetMapping(value = "/{id}")
    public ProjectDto findOne(@PathVariable Long id) {
        Project entity = projectService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.projectToProjectDTO(entity);
    }

    @Operation(
            summary = "${api.project.create-project.summary}",
            description = "${api.project.create-project.description}"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto create(@RequestBody ProjectDto newProject) {
        // this is needed for testing purposes
        // see @Valid for industrial-grade validation
        if (newProject.name() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Project entity = mapper.projectDTOToProject(newProject);
        return mapper.projectToProjectDTO(projectService.save(entity));
    }

    @Operation(
            summary = "${api.project.get-projects.summary}",
            description = "${api.project.get-projects.description}"
    )
    @GetMapping
    public Collection<ProjectDto> findAll() {
        Iterable<Project> allProjects = this.projectService.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        allProjects.forEach(p -> projectDtos.add(mapper.projectToProjectDTO(p)));
        return projectDtos;
    }

    @Operation(
            summary = "${api.project.update-project.summary}",
            description = "${api.project.update-project.description}"
    )
    @PutMapping("/{id}")
    public ProjectDto updateProject(@PathVariable("id") Long id, @RequestBody ProjectDto updatedProject) {
        Project entity = mapper.projectDTOToProject(updatedProject);
        return mapper.projectToProjectDTO(projectService.save(entity));
    }

    @Operation(
            summary = "${api.project.delete-project.summary}",
            description = "${api.project.delete-project.description}"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable("id") Long id) {
        projectService.deleteById(id);
    }
}