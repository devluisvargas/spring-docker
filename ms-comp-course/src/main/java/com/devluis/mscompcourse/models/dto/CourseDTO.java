package com.devluis.mscompcourse.models.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CourseDTO(Long id, @NotEmpty String name,  List<CourseUserDTO> courseUsers, List<UserDTO> users){}
