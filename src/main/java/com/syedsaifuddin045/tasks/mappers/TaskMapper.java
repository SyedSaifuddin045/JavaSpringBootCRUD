package com.syedsaifuddin045.tasks.mappers;

import com.syedsaifuddin045.tasks.domain.dto.TaskDTO;
import com.syedsaifuddin045.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDTO taskDTO);

    TaskDTO toDto(Task task);
}
