package com.syedsaifuddin045.tasks.mappers.impl;

import com.syedsaifuddin045.tasks.domain.dto.UserDTO;
import com.syedsaifuddin045.tasks.domain.entities.User;
import com.syedsaifuddin045.tasks.mappers.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if (user == null) return null;

        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getProfilePicture()
        );
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.id());
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setProfilePicture(dto.profilePicture());

        return user;
    }
}
