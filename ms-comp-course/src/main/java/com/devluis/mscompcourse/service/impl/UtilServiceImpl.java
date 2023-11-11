package com.devluis.mscompcourse.service.impl;

import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.dto.CourseUserDTO;
import com.devluis.mscompcourse.models.entity.Course;
import com.devluis.mscompcourse.models.entity.CourseUser;
import com.devluis.mscompcourse.service.UtilService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UtilServiceImpl implements UtilService {
    @Override
    public CourseDTO dtoToEntity(Course course) {
        List<CourseUserDTO> collect = course.getCourseUsers().stream().map(this::dtoToEntity).toList();
        return new CourseDTO(course.getId(), course.getName(), collect, List.of());
    }

    @Override
    public Course entityToDto(CourseDTO dto) {
        if(Objects.isNull(dto.courseUsers())){
            return new Course(dto.id(), dto.name(), List.of());
        }
        List<CourseUser> collect = dto.courseUsers().stream().map(this::entityToDto).toList();
        return new Course(dto.id(), dto.name(), collect);
    }

    @Override
    public CourseUserDTO dtoToEntity(CourseUser course) {
        return new CourseUserDTO(course.getId(), course.getUserId());
    }

    @Override
    public CourseUser entityToDto(CourseUserDTO dto) {
        return new CourseUser(dto.id(), dto.userId());
    }
}
