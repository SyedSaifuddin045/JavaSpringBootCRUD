package com.syedsaifuddin045.tasks.domain.dto;

import java.util.UUID;

public record UserDTO(
    UUID id,
    String name,
    String email,
    byte[] profilePicture
) {}
