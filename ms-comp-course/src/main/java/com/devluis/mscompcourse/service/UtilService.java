package com.devluis.mscompcourse.service;

import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.dto.CourseUserDTO;
import com.devluis.mscompcourse.models.entity.Course;
import com.devluis.mscompcourse.models.entity.CourseUser;

public interface UtilService {
    CourseDTO dtoToEntity(Course course);
    Course entityToDto(CourseDTO dto);
    CourseUserDTO dtoToEntity(CourseUser course);
    CourseUser entityToDto(CourseUserDTO dto);
}
