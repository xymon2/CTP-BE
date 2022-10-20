package com.litcode.server.repository;

import com.litcode.server.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUserIdAndPassword(String userId, String password);
}
