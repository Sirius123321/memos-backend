package org.lgz.memosbackend.service.impl;

import org.lgz.memosbackend.database.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Custom find by username method
 */
@Component
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public ReactiveUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Mono<UserDetails> findByUsername(String username) { // when throw UsernameNotFoundException, spring security will return login failed
        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + username)))  // if username don't in database, mono stream can start with empty.
                .map(userModel -> User.withUsername(userModel.getUsername())
                        .password(userModel.getPassword())
                        .roles("user")
                        .build());
    }
}
