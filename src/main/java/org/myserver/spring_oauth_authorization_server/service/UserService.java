package org.myserver.spring_oauth_authorization_server.service;

import lombok.RequiredArgsConstructor;
import org.myserver.spring_oauth_authorization_server.dto.UserDTO;
import org.myserver.spring_oauth_authorization_server.entity.UserEntity;
import org.myserver.spring_oauth_authorization_server.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원 가입
    public void join(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setRole("ADMIN"); // 접두사 생략

        userRepository.save(userEntity);
    }
}
