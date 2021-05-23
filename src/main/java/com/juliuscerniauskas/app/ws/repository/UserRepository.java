package com.juliuscerniauskas.app.ws.repository;

import com.juliuscerniauskas.app.ws.io.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByEmail(String email);
    boolean existsByEmail(String email);
}
