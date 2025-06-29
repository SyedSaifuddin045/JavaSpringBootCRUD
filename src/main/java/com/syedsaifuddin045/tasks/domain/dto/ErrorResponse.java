package com.syedsaifuddin045.tasks.domain.dto;

public record ErrorResponse(
    int status,
    String message,
    String details
) {

}
