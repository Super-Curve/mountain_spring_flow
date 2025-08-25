package com.mountain.web.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mountain.common.domain.AggregateRoot;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@TableName("users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AggregateRoot {
    
    private String username;
    
    private String email;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private UserStatus status = UserStatus.ACTIVE;
    
    @TableField(exist = false)
    private Set<String> roles = new HashSet<>();
    
    public enum UserStatus {
        ACTIVE, INACTIVE, SUSPENDED
    }
    
    public void activate() {
        this.status = UserStatus.ACTIVE;
    }
    
    public void deactivate() {
        this.status = UserStatus.INACTIVE;
    }
    
    public void addRole(String role) {
        this.roles.add(role);
    }
    
    public void removeRole(String role) {
        this.roles.remove(role);
    }
}