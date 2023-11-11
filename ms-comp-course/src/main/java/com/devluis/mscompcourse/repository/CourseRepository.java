package com.devluis.mscompcourse.repository;

import com.devluis.mscompcourse.models.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);

    @Modifying
    @Query("delete from CourseUser cu where cu.userId = :userId")
    void deleteCourseUserById(@Param("userId") Long userId);
}
