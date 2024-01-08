package com.baeldung.ls.mapper;

import com.baeldung.ls.persistence.model.Task;
import com.baeldung.ls.web.dto.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto taskToTaskDTO(Task entity);

    Task taskDTOToTask(TaskDto dto);
}
