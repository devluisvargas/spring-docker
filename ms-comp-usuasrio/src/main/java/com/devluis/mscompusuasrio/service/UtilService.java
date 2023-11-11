package com.devluis.mscompusuasrio.service;

import com.devluis.mscompusuasrio.models.dto.UserDTO;
import com.devluis.mscompusuasrio.models.entity.User;

public interface UtilService {
    UserDTO dtoToEntity(User user);
    User entityToDto(UserDTO user);
}
