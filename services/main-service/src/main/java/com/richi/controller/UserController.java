package com.richi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.richi.dto.UpdateUserRequest;
import com.richi.dto.UserRequest;
import com.richi.dto.UserResponse;
import com.richi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "CRUD для пользоватлей")
public class UserController {
    
    @Autowired private UserService userService;

    @Operation(summary = "Вывод списка всех пользоватлей")
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Вывод пользоватлея с заданным id")
    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findById(
        @PathVariable("user-id") int userId
    ){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @Operation(summary = "Создание нового пользователя")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
        @RequestBody @Valid UserRequest request
    ){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @Operation(summary = "Обновление данных пользователя")
    @PutMapping
    public ResponseEntity<Void> updateUser(
        @RequestBody @Valid UpdateUserRequest request
    ){
        userService.updateUser(request);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Удаление записи о пользователе")
    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable("user-id") int userId
    ){
        userService.deleteUserById(userId);
        return ResponseEntity.accepted().build();
    }
}
