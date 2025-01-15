package com.richi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ c токеном доступа")
public record JwtAuthenticationResponse (
    @Schema(description = "Токен доступа")
    String token
) {
    
}
