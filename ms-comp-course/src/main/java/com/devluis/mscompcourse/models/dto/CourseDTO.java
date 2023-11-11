package com.devluis.mscompcourse.models.dto;

import jakarta.validation.constraints.NotEmpty;

public record CourseDTO(Long id, @NotEmpty String name){}
