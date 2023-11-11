package com.devluis.mscompuser.service.impl;

import com.devluis.mscompuser.clients.CourseClientRest;
import com.devluis.mscompuser.models.dto.UserDTO;
import com.devluis.mscompuser.models.entity.User;
import com.devluis.mscompuser.repository.UserRepository;
import com.devluis.mscompuser.service.UserService;
import com.devluis.mscompuser.service.UtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UtilService utilService;
    private final CourseClientRest courseClientRest;
    private final Gson gson;

    @Override
    public List<UserDTO> findAll() {
        return this.userRepository.findAll().stream().map(utilService::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return utilService.dtoToEntity(user);
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        log.info("save user = {}", gson.toJson(dto));
        Optional<User> foundUser = this.userRepository.findByEmail(dto.email());
        if (foundUser.isPresent()) {
            log.error("Already user with email {}", dto.email());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already user with email ".concat(dto.email()));
        }
        User user = this.utilService.entityToDto(dto);
        user.setId(null); // reset id because it's autoincrement.
        return utilService.dtoToEntity(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.findById(id);
        log.info("delete user with id = {}", id);
        try{
            courseClientRest.deleteCourseUserById(id);
        }catch (Exception e){
            log.error("error with delete assign user in course {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }
        this.userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        UserDTO foundUser = this.findById(id);
        User user = this.utilService.entityToDto(foundUser);
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return this.utilService.dtoToEntity(this.userRepository.save(user));
    }

    @Override
    public List<UserDTO> findAllById(Iterable<Long> ids) {
        log.info("findAllById {}", gson.toJson(ids));
        return this.userRepository.findAllById(ids).stream().map(utilService::dtoToEntity).toList();
    }
}
