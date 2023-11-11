package com.devluis.mscompuser.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-comp-course", url = "${ms.course.url}")
public interface CourseClientRest {

    @DeleteMapping("/deleteUser/{id}")
    void deleteCourseUserById(@PathVariable Long id);
}
