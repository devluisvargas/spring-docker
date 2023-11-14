package com.devluis.mscompcourse.clients;

import com.devluis.mscompcourse.models.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-comp-user")
public interface UserClientRest {
    @GetMapping("/user/{id}")
    UserDTO findById(@PathVariable Long id);

    @PostMapping("/user")
    UserDTO create(@RequestBody UserDTO userDTO);

    @GetMapping("/user/course")
    List<UserDTO> findUsers(@RequestParam Iterable<Long> ids);
}
