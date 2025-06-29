package com.syedsaifuddin045.tasks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.syedsaifuddin045.tasks.domain.entities.TaskList;

public interface TaskListService {
    List<TaskList> listTaskLists();

    TaskList createTaskList(TaskList taskList);

    Optional<TaskList> getTaskListbyId(UUID id);
    TaskList UpdateTaskList(UUID tasklistId,TaskList taskList);
    void deleteTask(UUID taskID);
}
