package com.syedsaifuddin045.tasks.services;

import java.util.List;
import java.util.UUID;

import com.syedsaifuddin045.tasks.domain.entities.Task;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId,Task task);
}
