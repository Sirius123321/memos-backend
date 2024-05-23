package org.lgz.memosbackend.service.impl;

import org.lgz.memosbackend.database.dao.UserRepository;
import org.lgz.memosbackend.database.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public ReactiveUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return null;
    }
}
