package com.mountain.web.application.service;

import com.mountain.common.application.UseCase;
import com.mountain.web.application.dto.UserCreateDto;
import com.mountain.web.application.dto.UserDto;
import com.mountain.web.application.mapper.UserMapper;
import com.mountain.web.domain.model.User;
import com.mountain.web.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(UserCreateDto createDto) {
        if (userRepository.existsByUsername(createDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        if (userRepository.existsByEmail(createDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = userMapper.toEntity(createDto);
        user.addRole("USER");
        
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}