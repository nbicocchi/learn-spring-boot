package com.baeldung.ls.web.dto;

import com.baeldung.ls.persistence.model.TaskStatus;

import java.time.LocalDate;

public record TaskDto(Long id, String name, String description, LocalDate dateCreated, LocalDate dueDate,
                      TaskStatus status) {
}
