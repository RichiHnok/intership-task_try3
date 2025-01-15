package com.richi.service;

import org.springframework.stereotype.Service;

import com.richi.dto.UserRequest;
import com.richi.dto.UserResponse;
import com.richi.entity.User;

@Service
public class UserMapper {
    
    public UserResponse toUserResponse(User user){
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getRole()
        );
    }

    public User toUser(UserRequest request){
        return User.builder()
            .username(request.username())
            .password(request.password())
            .email(request.email())
            .role(request.role())
            .build();
    }
}
