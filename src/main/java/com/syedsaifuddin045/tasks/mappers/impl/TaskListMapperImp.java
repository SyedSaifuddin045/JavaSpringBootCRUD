package com.syedsaifuddin045.tasks.mappers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.syedsaifuddin045.tasks.domain.dto.TaskListDTO;
import com.syedsaifuddin045.tasks.domain.entities.Task;
import com.syedsaifuddin045.tasks.domain.entities.TaskList;
import com.syedsaifuddin045.tasks.domain.entities.TaskStatus;
import com.syedsaifuddin045.tasks.mappers.TaskListMapper;
import com.syedsaifuddin045.tasks.mappers.TaskMapper;

@Component
public class TaskListMapperImp implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImp(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProegress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null));
    }

    @Override
    public TaskList fromDto(TaskListDTO taskListDTO) {
        return new TaskList(
                taskListDTO.id(),
                taskListDTO.title(),
                taskListDTO.description(),
                Optional.ofNullable(taskListDTO.tasks())
                        .map(tasks -> tasks.stream().map(taskMapper::fromDto)
                                .toList())
                        .orElse(null),
                null,
                null);
    }

    private Double calculateTaskListProegress(List<Task> tasks) {
        if (null == tasks)
            return null;

        long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();
        return (double) closedTaskCount / tasks.size();
    }
}
