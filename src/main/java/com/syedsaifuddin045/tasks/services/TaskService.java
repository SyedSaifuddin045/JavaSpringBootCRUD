package com.syedsaifuddin045.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.syedsaifuddin045.tasks.domain.entities.Task;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);

    Task createTask(UUID taskListId, Task task);

    Optional<Task> getTask(UUID taskListId, UUID taskId);

    Task updateTask(UUID taskListId, UUID taskId, Task task);

    void deleteTask(UUID taskListId, UUID taskId);
}
