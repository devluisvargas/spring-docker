package com.devluis.mscompusuasrio.service.impl;

import com.devluis.mscompusuasrio.models.dto.UserDTO;
import com.devluis.mscompusuasrio.models.entity.User;
import com.devluis.mscompusuasrio.service.UtilService;
import org.springframework.stereotype.Component;

@Component
public class UtilServiceImpl implements UtilService {
    @Override
    public UserDTO dtoToEntity(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    @Override
    public User entityToDto(UserDTO dto) {
        return new User(dto.id(), dto.name(), dto.email(), dto.password());
    }
}
