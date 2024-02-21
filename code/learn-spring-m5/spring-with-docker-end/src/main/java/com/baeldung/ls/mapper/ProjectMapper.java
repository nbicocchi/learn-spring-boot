package com.baeldung.ls.mapper;

import com.baeldung.ls.persistence.model.Project;
import com.baeldung.ls.web.dto.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface ProjectMapper {
    ProjectDto projectToProjectDTO(Project entity);

    Project projectDTOToProject(ProjectDto dto);
}
