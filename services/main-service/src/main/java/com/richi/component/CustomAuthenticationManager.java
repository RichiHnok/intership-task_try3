package com.richi.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.richi.entity.User;
import com.richi.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Component
public class CustomAuthenticationManager implements AuthenticationManager{

    @Autowired private UserService userService;
    @Autowired @Lazy private PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        User user = null;
        try {
            user = userService.findByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new BadCredentialsException("1000");
        }
        // if (user == null) {
        //     throw new BadCredentialsException("1000");
        // }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }
        return null;
        // if (user.isDisabled()) {
        //     throw new DisabledException("1001");
        // }
        // List<Right> userRights = rightRepo.getUserRights(username);
        // return new UsernamePasswordAuthenticationToken(username, null, userRights.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList()));
    }
}
