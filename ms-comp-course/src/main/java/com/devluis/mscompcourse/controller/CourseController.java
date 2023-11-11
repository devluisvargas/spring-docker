package com.devluis.mscompcourse.controller;

import com.devluis.mscompcourse.models.dto.CourseDTO;
import com.devluis.mscompcourse.models.dto.UserDTO;
import com.devluis.mscompcourse.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "**")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findByIdWithUsers(id));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@RequestBody @Valid CourseDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody @Valid CourseDTO dto) {
        return ResponseEntity.ok(courseService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        this.courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/assignUser/{courseId}")
    public ResponseEntity<UserDTO> assignUser(@PathVariable Long courseId, @RequestBody @Valid UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.assignUser(dto, courseId));
    }


    @PutMapping("/unAssignUser/{courseId}")
    public ResponseEntity<UserDTO> unAssignUser(@PathVariable Long courseId, @RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(courseService.unAssignUser(dto, courseId));
    }

    @PutMapping("/createUser/{courseId}")
    public ResponseEntity<UserDTO> createUser(@PathVariable Long courseId, @RequestBody @Valid UserDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createUser(dto, courseId));
    }

}
