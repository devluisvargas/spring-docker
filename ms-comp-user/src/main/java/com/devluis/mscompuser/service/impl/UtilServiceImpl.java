package com.devluis.mscompuser.service.impl;

import com.devluis.mscompuser.models.dto.UserDTO;
import com.devluis.mscompuser.models.entity.User;
import com.devluis.mscompuser.service.UtilService;
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
