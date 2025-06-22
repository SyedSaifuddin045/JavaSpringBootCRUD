package com.syedsaifuddin045.tasks.mappers.impl;

import org.springframework.stereotype.Component;

import com.syedsaifuddin045.tasks.domain.dto.TaskDTO;
import com.syedsaifuddin045.tasks.domain.entities.Task;
import com.syedsaifuddin045.tasks.mappers.TaskMapper;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDTO taskDTO) {
        return new Task(
                taskDTO.id(),
                taskDTO.title(),
                taskDTO.description(),
                taskDTO.dueDate(),
                taskDTO.status(),
                taskDTO.priority(),
                null,
                null,
                null);
    }

    @Override
    public TaskDTO toDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus());
    }

}
