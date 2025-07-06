package com.syedsaifuddin045.tasks.mappers;

import com.syedsaifuddin045.tasks.domain.dto.UserDTO;
import com.syedsaifuddin045.tasks.domain.entities.User;

public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
