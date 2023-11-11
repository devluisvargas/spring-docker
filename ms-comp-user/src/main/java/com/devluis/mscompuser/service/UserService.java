package com.devluis.mscompuser.service;

import com.devluis.mscompuser.models.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO save(UserDTO dto);

    void delete(Long id);

    UserDTO update(Long id, UserDTO dto);

    List<UserDTO> findAllById(Iterable<Long> id);
}
