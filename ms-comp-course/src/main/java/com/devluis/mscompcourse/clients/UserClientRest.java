package com.devluis.mscompcourse.clients;

import com.devluis.mscompcourse.models.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-comp-user", url = "${ms.user.url}")
public interface UserClientRest {
    @GetMapping("/{id}")
    UserDTO findById(@PathVariable Long id);

    @PostMapping
    UserDTO create(@RequestBody UserDTO userDTO);

    @GetMapping("/course")
    List<UserDTO> findUsers(@RequestParam Iterable<Long> ids);
}
