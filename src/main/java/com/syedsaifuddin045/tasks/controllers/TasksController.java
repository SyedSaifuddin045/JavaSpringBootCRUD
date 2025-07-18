package com.syedsaifuddin045.tasks.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syedsaifuddin045.tasks.domain.dto.TaskDTO;
import com.syedsaifuddin045.tasks.domain.entities.Task;
import com.syedsaifuddin045.tasks.mappers.TaskMapper;
import com.syedsaifuddin045.tasks.services.TaskService;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TasksController {
    private final TaskService taskService;

    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDTO> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
    }

    @PostMapping
    public TaskDTO createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskListId, taskMapper.fromDto(taskDTO));
        return taskMapper.toDto(createdTask);
    }

    @GetMapping("/{task_id}")
    public Optional<TaskDTO> getTask(@PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        return taskService.getTask(taskListId, taskId).map(taskMapper::toDto);
    }

    @PutMapping("/{task_id}")
    public TaskDTO updateTask(@PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId, @RequestBody TaskDTO taskDTO) {
        return taskMapper.toDto(taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDTO)));
    }

    public void deleteTask(@PathVariable("task_list_id") UUID taskListId,
            @PathVariable("task_id") UUID taskId) {
        taskService.deleteTask(taskListId, taskId);
    }
}
