package com.devluis.mscompcourse.service;

import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.dto.UserDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    CourseDTO findById(Long id);
    CourseDTO findByIdWithUsers(Long id);
    CourseDTO save(CourseDTO dto);
    void delete(Long id);
    CourseDTO update(Long id, CourseDTO dto);
    UserDTO assignUser(UserDTO userDTO, Long courseId);
    UserDTO createUser(UserDTO userDTO, Long courseId);
    UserDTO unAssignUser(UserDTO userDTO, Long courseId);
}
