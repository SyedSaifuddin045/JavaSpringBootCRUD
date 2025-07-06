package com.syedsaifuddin045.tasks.mappers.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.syedsaifuddin045.tasks.domain.dto.TaskListDTO;
import com.syedsaifuddin045.tasks.domain.entities.Task;
import com.syedsaifuddin045.tasks.domain.entities.TaskList;
import com.syedsaifuddin045.tasks.domain.entities.TaskStatus;
import com.syedsaifuddin045.tasks.mappers.TaskListMapper;
import com.syedsaifuddin045.tasks.mappers.TaskMapper;
import com.syedsaifuddin045.tasks.mappers.UserMapper;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    public TaskListMapperImpl(TaskMapper taskMapper, UserMapper userMapper) {
        this.taskMapper = taskMapper;
        this.userMapper = userMapper;
    }

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                userMapper.toDTO(taskList.getOwner()),
                Optional.ofNullable(taskList.getTasks()).map(List::size).orElse(0),
                calculateTaskListProgress(taskList.getTasks()),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream().map(taskMapper::toDto).toList())
                        .orElse(null),
                Optional.ofNullable(taskList.getCollaborators())
                        .map(users -> users.stream().map(userMapper::toDTO).collect(Collectors.toSet()))
                        .orElse(Set.of()));
    }

    @Override
    public TaskList fromDto(TaskListDTO taskListDTO) {
        TaskList taskList = new TaskList();

        taskList.setId(taskListDTO.id());
        taskList.setTitle(taskListDTO.title());
        taskList.setDescription(taskListDTO.description());

        if (taskListDTO.tasks() != null) {
            List<Task> tasks = taskListDTO.tasks().stream()
                    .map(taskMapper::fromDto)
                    .toList();
            taskList.setTasks(tasks);
        }

        if (taskListDTO.owner() != null) {
            taskList.setOwner(userMapper.toEntity(taskListDTO.owner()));
        }

        if (taskListDTO.collaborators() != null) {
            Set<com.syedsaifuddin045.tasks.domain.entities.User> collaborators = taskListDTO.collaborators().stream()
                    .map(userMapper::toEntity)
                    .collect(Collectors.toSet());
            taskList.setCollaborators(collaborators);
        }

        return taskList;
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty())
            return 0.0;

        long closedTaskCount = tasks.stream()
                .filter(task -> TaskStatus.CLOSED == task.getStatus())
                .count();

        return (double) closedTaskCount / tasks.size();
    }
}
