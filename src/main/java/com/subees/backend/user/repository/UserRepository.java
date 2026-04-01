package com.subees.backend.user.repository;

import com.subees.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<User> findByEmail(String email);
}