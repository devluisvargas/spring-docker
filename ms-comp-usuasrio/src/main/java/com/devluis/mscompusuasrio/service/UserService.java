package com.devluis.mscompusuasrio.service;

import com.devluis.mscompusuasrio.models.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserDTO dto);
    void delete(Long id);
    UserDTO update(Long id, UserDTO dto);
}
