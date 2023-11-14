package com.devluis.mscompuser.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-comp-course")
public interface CourseClientRest {

    @DeleteMapping("/course/deleteUser/{id}")
    void deleteCourseUserById(@PathVariable Long id);
}
