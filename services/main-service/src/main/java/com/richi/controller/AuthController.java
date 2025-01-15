package com.richi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.richi.dto.JwtAuthenticationResponse;
import com.richi.dto.SignInRequest;
import com.richi.dto.SignUpRequest;
import com.richi.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Аутентификация")
public class AuthController {
    
    @Autowired private AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(
        @RequestBody @Valid SignUpRequest request
    ){
        return authService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(
        @RequestBody @Valid SignInRequest request
    ){
        return authService.signIn(request);
    }
}
