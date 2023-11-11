package com.devluis.mscompusuasrio.service.impl;

import com.devluis.mscompusuasrio.models.dto.UserDTO;
import com.devluis.mscompusuasrio.models.entity.User;
import com.devluis.mscompusuasrio.repository.UserRepository;
import com.devluis.mscompusuasrio.service.UserService;
import com.devluis.mscompusuasrio.service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UtilService utilService;

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
    public UserDTO save(UserDTO dto) {
        Optional<User> foundUser = this.userRepository.findByEmail(dto.email());
        if (foundUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already user with email ".concat(dto.email()));
        }
        User user = this.utilService.entityToDto(dto);
        user.setId(null); // reset id because it's autoincrement.
        return utilService.dtoToEntity(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        this.findById(id);
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDTO update(Long id, UserDTO dto) {
        UserDTO foundUser = this.findById(id);
        User user = this.utilService.entityToDto(foundUser);
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return this.utilService.dtoToEntity(this.userRepository.save(user));
    }
}
