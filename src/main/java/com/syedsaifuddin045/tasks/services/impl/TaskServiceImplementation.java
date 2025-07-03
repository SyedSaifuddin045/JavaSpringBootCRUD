package com.syedsaifuddin045.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.syedsaifuddin045.tasks.domain.entities.Task;
import com.syedsaifuddin045.tasks.domain.entities.TaskList;
import com.syedsaifuddin045.tasks.domain.entities.TaskPriority;
import com.syedsaifuddin045.tasks.domain.entities.TaskStatus;
import com.syedsaifuddin045.tasks.repositories.TaskListRepository;
import com.syedsaifuddin045.tasks.repositories.TaskRepository;
import com.syedsaifuddin045.tasks.services.TaskService;

@Service
public class TaskServiceImplementation implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImplementation(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if (taskListId == null)
            throw new IllegalArgumentException("Missing Task List Id to create task for.");
        if (task.getId() != null)
            throw new IllegalArgumentException("The task already contains an Id.");
        if (task.getTitle() == null)
            throw new IllegalArgumentException("The task is missing a title");

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID"));

        LocalDateTime now = LocalDateTime.now();
        Task createdTask = taskRepository.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus, taskPriority, taskList, now, now));
        return createdTask;
    }

}
