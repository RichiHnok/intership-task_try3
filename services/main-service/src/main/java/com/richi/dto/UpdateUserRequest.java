package com.richi.dto;

import com.richi.entity.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Запрос на обновление данных о пользователе")
public record UpdateUserRequest(
    @NotNull
    Integer id,
    String username,
    String password,
    String email,
    Role role
) {
    
}
