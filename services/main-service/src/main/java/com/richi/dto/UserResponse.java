package com.richi.dto;

import com.richi.entity.Role;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ с данными о пользователе")
public record UserResponse (
    Integer id,
    String username,
    String password,
    String email,
    Role role
) {
    
}
