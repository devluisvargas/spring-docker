package com.devluis.mscompcourse.service;

import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.entity.Course;

public interface UtilService {
    CourseDTO dtoToEntity(Course course);
    Course entityToDto(CourseDTO dto);
}
