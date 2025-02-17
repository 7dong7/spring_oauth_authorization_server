package org.myserver.spring_oauth_authorization_server.repository;

import org.myserver.spring_oauth_authorization_server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
