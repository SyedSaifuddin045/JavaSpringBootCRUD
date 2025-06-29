package com.syedsaifuddin045.tasks.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.syedsaifuddin045.tasks.domain.entities.TaskList;
import com.syedsaifuddin045.tasks.repositories.TaskListRepository;
import com.syedsaifuddin045.tasks.services.TaskListService;

@Service
public class TaskListServiceImplementation implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImplementation(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("Task List already has an ID");
        }
        if (taskList.getTitle() == null) {
            throw new IllegalArgumentException("Task List Title must be present!");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now));
    }

}
