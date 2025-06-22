package com.syedsaifuddin045.tasks.mappers;

import com.syedsaifuddin045.tasks.domain.dto.TaskListDTO;
import com.syedsaifuddin045.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    TaskListDTO toDto(TaskList taskList);

    TaskList fromDto(TaskListDTO taskListDTO);
}
