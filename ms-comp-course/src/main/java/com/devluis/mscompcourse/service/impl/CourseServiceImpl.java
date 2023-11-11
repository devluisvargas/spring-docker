package com.devluis.mscompcourse.service.impl;


import com.devluis.mscompcourse.clients.UserClientRest;
import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.dto.UserDTO;
import com.devluis.mscompcourse.models.entity.Course;
import com.devluis.mscompcourse.models.entity.CourseUser;
import com.devluis.mscompcourse.repository.CourseRepository;
import com.devluis.mscompcourse.service.CourseService;
import com.devluis.mscompcourse.service.UtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UtilService utilService;
    private final UserClientRest userClientRest;
    private final Gson gson = new Gson();

    @Override
    public List<CourseDTO> findAll() {
        return this.courseRepository.findAll().stream().map(utilService::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public CourseDTO findById(Long id) {
        log.info("findById course {}", id);
        Course Course = this.courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return utilService.dtoToEntity(Course);
    }

    @Override
    public CourseDTO save(CourseDTO dto) {
        log.info("save course {}",gson.toJson(dto));
        Optional<Course> foundCourse = this.courseRepository.findByName(dto.name());
        if (foundCourse.isPresent()) {
            log.error("Already Course with name {}", dto.name());
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

    @Override
    @Transactional
    public UserDTO assignUser(UserDTO userDTO, Long courseId) {
        log.info("assignUser {} courseId {}", gson.toJson(userDTO), courseId);
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Course not found"));
        UserDTO resultUser;
        try {
            resultUser = userClientRest.findById(userDTO.id());
        } catch (Exception e) {
            log.error("assignUser error {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(resultUser.id());
        course.addCourseUser(courseUser);
        return resultUser;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO, Long courseId) {
        log.info("createUser {} courseId {}", gson.toJson(userDTO), courseId);
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Course not found"));
        UserDTO resultUser;
        try {
            resultUser = userClientRest.create(userDTO);
        } catch (Exception e) {
            log.error("createUser error {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(resultUser.id());
        course.addCourseUser(courseUser);
        return resultUser;
    }

    @Override
    @Transactional
    public UserDTO unAssignUser(UserDTO userDTO, Long courseId) {
        log.info("unAssignUser {} courseId {}", gson.toJson(userDTO), courseId);
        Course courseFound = this.courseRepository.findById(courseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "no found Course"));
        UserDTO resultUser;
        try {
            resultUser = userClientRest.findById(userDTO.id());
        } catch (Exception e) {
            log.error("unAssignUser error {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(resultUser.id());
        courseFound.removeCourseUser(courseUser);
        return resultUser;
    }
}
