package com.richi.dto;

import jakarta.validation.constraints.NotBlank;

public record DocumentToStoreRequest(
    @NotBlank
    String content
) {
    
}
