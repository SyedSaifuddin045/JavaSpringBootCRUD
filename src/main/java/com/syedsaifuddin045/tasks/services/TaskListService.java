package com.syedsaifuddin045.tasks.services;

import java.util.List;

import com.syedsaifuddin045.tasks.domain.entities.TaskList;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
}
