package com.litcode.server.repository;

import com.litcode.server.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    User findByUserIdAndPassword(String userId, String password);
}
