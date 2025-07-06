package com.syedsaifuddin045.tasks.services;

import com.syedsaifuddin045.tasks.domain.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO userDTO);

    String login(UserDTO userDTO); // returns a token or session

    UserDTO getCurrentUser();
}
