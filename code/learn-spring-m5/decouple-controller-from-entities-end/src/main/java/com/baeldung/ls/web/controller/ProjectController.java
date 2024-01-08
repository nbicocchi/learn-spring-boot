package com.baeldung.ls.web.controller;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.persistence.model.Task;
import com.baeldung.ls.service.IProjectService;
import com.baeldung.ls.web.dto.ProjectDto;
import com.baeldung.ls.web.dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{id}")
    public ProjectDto findOne(@PathVariable Long id) {
        Project entity = projectService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return convertToDto(entity);
    }

    protected ProjectDto convertToDto(Project entity) {
        return new ProjectDto(entity.getId(), entity.getName(), entity.getDateCreated(), entity.getTasks().stream().map(t -> convertTaskToDto(t)).collect(Collectors.toSet()));
    }

    protected TaskDto convertTaskToDto(Task entity) {
        TaskDto dto = new TaskDto(entity.getId(), entity.getName(), entity.getDescription(), entity.getDateCreated(), entity.getDueDate(), entity.getStatus());
        return dto;
    }

    @PostMapping
    public void create(@RequestBody ProjectDto newProject) {
        Project entity = convertToEntity(newProject);
        this.projectService.save(entity);
    }

    protected Project convertToEntity(ProjectDto dto) {
        Project project = new Project(dto.name(), dto.dateCreated());
        if (!Objects.isNull(dto.id())) {
            project.setId(dto.id());
        }
        return project;
    }

    protected Task convertTaskToEntity(TaskDto dto) {
        Task task = new Task(dto.name(), dto.description(), dto.dateCreated(), dto.dueDate(), dto.status());
        if (!Objects.isNull(dto.id())) {
            task.setId(dto.id());
        }
        return task;
    }
}
