package com.syedsaifuddin045.tasks.domain.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(
        UUID id,
        String name,
        String email,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        byte[] profilePicture) {
}
