package com.syedsaifuddin045.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

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

        if (taskList.getOwner() == null) {
            throw new IllegalArgumentException("Task List must have an owner!");
        }

        LocalDateTime now = LocalDateTime.now();

        taskList.setCreatedAt(now);
        taskList.setUpdatedAt(now);

        return taskListRepository.save(taskList);
    }

    @Override
    public Optional<TaskList> getTaskListbyId(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList UpdateTaskList(UUID tasklistId, TaskList taskList) {
        if (taskList.getId() == null)
            throw new IllegalArgumentException("Task List must have an ID.");

        if (!Objects.equals(taskList.getId(), tasklistId))
            throw new IllegalArgumentException("Attempting to change task list ID, this is not permitted");

        TaskList existingTaskList = taskListRepository.findById(tasklistId)
                .orElseThrow(() -> new IllegalArgumentException("Task List not found!"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdatedAt(LocalDateTime.now());

        if (taskList.getCollaborators() != null) {
            existingTaskList.setCollaborators(taskList.getCollaborators());
        }

        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTask(UUID taskID) {
        if (taskID == null)
            throw new IllegalArgumentException("Missing ID of the Task List to delete.");

        taskListRepository.deleteById(taskID);
    }
}
