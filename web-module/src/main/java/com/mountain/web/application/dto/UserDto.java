package com.mountain.web.application.dto;

import com.mountain.web.domain.model.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private User.UserStatus status;
    private Set<String> roles;
}