package com.devluis.mscompcourse.clients;

import com.devluis.mscompcourse.models.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-comp-user", url = "localhost:8001/user")
public interface UserClientRest {
    @GetMapping("/{id}")
    UserDTO findById(@PathVariable Long id);

    @PostMapping
    UserDTO create(@RequestBody UserDTO userDTO);
}
