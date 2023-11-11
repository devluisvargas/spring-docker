package com.devluis.mscompcourse.service.impl;

import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.entity.Course;
import com.devluis.mscompcourse.service.UtilService;
import org.springframework.stereotype.Component;

@Component
public class UtilServiceImpl implements UtilService {
    @Override
    public CourseDTO dtoToEntity(Course course) {
        return new CourseDTO(course.getId(), course.getName());
    }

    @Override
    public Course entityToDto(CourseDTO dto) {
        return new Course(dto.id(), dto.name());
    }
}
