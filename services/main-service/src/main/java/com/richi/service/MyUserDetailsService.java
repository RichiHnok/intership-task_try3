package com.richi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.richi.config.MyUserDetails;
import com.richi.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
    
    @Autowired private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
