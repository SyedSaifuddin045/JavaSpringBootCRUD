package com.syedsaifuddin045.tasks.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syedsaifuddin045.tasks.domain.dto.TaskListDTO;
import com.syedsaifuddin045.tasks.domain.entities.TaskList;
import com.syedsaifuddin045.tasks.mappers.TaskListMapper;
import com.syedsaifuddin045.tasks.services.TaskListService;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDTO> listTaskLists() {
        return taskListService.listTaskLists()
                .stream().map(taskListMapper::toDto).toList();
    }

    @PostMapping("/new")
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO) {
        TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDTO));
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public Optional<TaskListDTO> getTaskList(@PathVariable("task_list_id") UUID taskListID) {
        return taskListService.getTaskListbyId(taskListID).map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{task_list_id}")
    public TaskListDTO updateTaskList(@PathVariable("task_list_id") UUID taskListID,
            @RequestBody TaskListDTO taskListDTO) {
        TaskList updatedTaskList = taskListService.UpdateTaskList(taskListID, taskListMapper.fromDto(taskListDTO));
        return taskListMapper.toDto(updatedTaskList);
    }

    @DeleteMapping(path = "/{task_list_id}")
    public void deleteTaskById(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTask(taskListId);
    }
}
