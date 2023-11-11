package com.devluis.mscompcourse.service;

import com.devluis.mscompcourse.models.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    CourseDTO findById(Long id);
    CourseDTO save(CourseDTO dto);
    void delete(Long id);
    CourseDTO update(Long id, CourseDTO dto);
}
