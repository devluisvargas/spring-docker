package com.devluis.mscompcourse.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<CourseUser> courseUsers = new ArrayList<>();

    public void addCourseUser(CourseUser courseUser){
        courseUsers.add(courseUser);
    }
    public void removeCourseUser(CourseUser courseUser){
        courseUsers.remove(courseUser);
    }
}
