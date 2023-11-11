package com.devluis.mscompuser.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(Long id, @NotEmpty String name, @NotEmpty @Email String email, @NotEmpty String password){}
