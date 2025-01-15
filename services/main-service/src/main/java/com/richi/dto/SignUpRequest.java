package com.richi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на регистрацию")
public record SignUpRequest(
    @Schema(description = "Имя пользователя", example = "Jon")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    String username,

    @Schema(description = "Адрес электронной почты", example = "jondoe@gmail.com")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    String email,
    
    @Schema(description = "Пароль", example = "my_1secret1_password")
    @NotBlank(message = "Пароль не может быть пустыми")
    String password
) {
    
}
