package com.syedsaifuddin045.tasks.domain.dto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record TaskListDTO(
        UUID id,
        String title,
        String description,
        UserDTO owner,
        Integer count,
        Double progress,
        List<TaskDTO> tasks,
        Set<UserDTO> collaborators) {

}
