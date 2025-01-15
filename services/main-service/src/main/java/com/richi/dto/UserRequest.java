package com.richi.dto;

import com.richi.entity.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Запрос на создание пользователя")
public record UserRequest(
    Integer id,
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    String username,
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    String password,
    @NotBlank(message = "Пароль не может быть пустыми")
    String email,
    @NotNull(message = "Роль должна быть указана")
    Role role
) {
    
}
