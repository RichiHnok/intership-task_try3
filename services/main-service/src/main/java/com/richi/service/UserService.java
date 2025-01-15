package com.richi.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.richi.dto.UpdateUserRequest;
import com.richi.dto.UserRequest;
import com.richi.dto.UserResponse;
import com.richi.entity.User;
import com.richi.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    
    @Autowired private UserRepository repository;
    @Autowired private UserMapper mapper;
    @Autowired @Lazy private PasswordEncoder passwordEncoder;

    public List<UserResponse> findAll(){
        return repository.findAll().stream()
            .map(mapper::toUserResponse)
            .toList();
    }

    public UserResponse findById(int id){
        var user = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("There is no user with ID:: " + id));
        return mapper.toUserResponse(user);
    }

    public void updateUser(UpdateUserRequest request){
        var user = repository.findById(request.id()).orElseThrow(
            () -> new EntityNotFoundException("There is no user with ID:: " + request.id())
        );
        mergeUser(user, request);
        repository.save(user);
    }

    public void mergeUser(User user, UpdateUserRequest request){
        if(StringUtils.isNotBlank(request.username())){
            user.setUsername(request.username());
        }
        if(StringUtils.isNotBlank(request.email())){
            user.setEmail(request.email());
        }
        if(StringUtils.isNotBlank(request.password())){
            user.setPassword(passwordEncoder.encode(request.password()));
        }
        if(request.role() != null){
            user.setRole(request.role());
        }
    }

    public UserResponse createUser(UserRequest request){
        var user = mapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toUserResponse(repository.save(user));
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public void deleteUserById(int userId){
        repository.findById(userId).orElseThrow(() -> new EntityNotFoundException("There is no user with ID:: " + userId));
        repository.deleteById(userId);
    }

    public User getCurrentUser(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByUsername(username);
    }

    public User findByUsername(String username){
        return repository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("There is no user with username " + username));
    }
}
