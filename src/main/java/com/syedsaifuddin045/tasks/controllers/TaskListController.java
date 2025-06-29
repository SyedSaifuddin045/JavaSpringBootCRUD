package com.syedsaifuddin045.tasks.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public TaskListDTO createTaskList(@RequestBody TaskListDTO taskListDTO)
    {
        TaskList createdTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDTO));
        return taskListMapper.toDto(createdTaskList);
    }
}
