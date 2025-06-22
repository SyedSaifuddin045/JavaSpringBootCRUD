package com.syedsaifuddin045.tasks.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.syedsaifuddin045.tasks.domain.entities.TaskPriority;
import com.syedsaifuddin045.tasks.domain.entities.TaskStatus;

public record TaskDTO(
    UUID id,
    String title,
    String description,
    LocalDateTime dueDate,
    TaskPriority priority,
    TaskStatus status
) {

}
 