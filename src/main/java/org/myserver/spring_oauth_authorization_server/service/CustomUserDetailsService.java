package org.myserver.spring_oauth_authorization_server.service;

import lombok.RequiredArgsConstructor;
import org.myserver.spring_oauth_authorization_server.entity.UserEntity;
import org.myserver.spring_oauth_authorization_server.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUsername(username).orElseThrow();

        UserDetails userDetails = User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .roles(entity.getRole())
                .build();

        return userDetails;
    }
}
