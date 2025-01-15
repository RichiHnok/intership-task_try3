package com.richi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.richi.dto.JwtAuthenticationResponse;
import com.richi.dto.SignInRequest;
import com.richi.dto.SignUpRequest;
import com.richi.entity.Role;
import com.richi.entity.User;

@Service
public class AuthService {
    
    @Autowired private JwtService jwtService;
    @Autowired private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest request){
        var user = User.builder()
            .username(request.username())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .role(Role.ROLE_USER)
            .build();

        userService.createUser(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.username()
                , request.password()
            )
        );

        var user = userService.findByUsername(request.username());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
