package com.richi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на аутентификацию")
public record SignInRequest(

    @Schema(description = "Имя пользователя", example = "Jon")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    String username,

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @NotBlank(message = "Пароль не может быть пустыми")
    String password
) {
    
}
