package com.devluis.mscompcourse.service.impl;


import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.entity.Course;
import com.devluis.mscompcourse.repository.CourseRepository;
import com.devluis.mscompcourse.service.CourseService;
import com.devluis.mscompcourse.service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UtilService utilService;

    @Override
    public List<CourseDTO> findAll() {
        return this.courseRepository.findAll().stream().map(utilService::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public CourseDTO findById(Long id) {
        Course Course = this.courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return utilService.dtoToEntity(Course);
    }

    @Override
    public CourseDTO save(CourseDTO dto) {
        Optional<Course> foundCourse = this.courseRepository.findByName(dto.name());
        if (foundCourse.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already Course with name ".concat(dto.name()));
        }
        Course Course = this.utilService.entityToDto(dto);
        Course.setId(null); // reset id because it's autoincrement.
        return utilService.dtoToEntity(courseRepository.save(Course));
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        this.courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO update(Long id, CourseDTO dto) {
        CourseDTO foundCourse = this.findById(id);
        Course Course = this.utilService.entityToDto(foundCourse);
        Course.setName(dto.name());
        return this.utilService.dtoToEntity(this.courseRepository.save(Course));
    }
}
