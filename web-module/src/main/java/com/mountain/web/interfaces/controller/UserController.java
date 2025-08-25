package com.mountain.web.interfaces.controller;

import com.mountain.common.interfaces.dto.BaseResponse;
import com.mountain.web.application.dto.UserCreateDto;
import com.mountain.web.application.dto.UserDto;
import com.mountain.web.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponse<UserDto>> createUser(@Valid @RequestBody UserCreateDto createDto) {
        UserDto user = userService.createUser(createDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.success("User created successfully", user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserDto>> getUser(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(BaseResponse.success(user));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(BaseResponse.success(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(BaseResponse.success("User deleted successfully", null));
    }
}