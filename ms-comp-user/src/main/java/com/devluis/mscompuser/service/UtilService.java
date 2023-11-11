package com.devluis.mscompuser.service;

import com.devluis.mscompuser.models.dto.UserDTO;
import com.devluis.mscompuser.models.entity.User;

public interface UtilService {
    UserDTO dtoToEntity(User user);
    User entityToDto(UserDTO user);
}
