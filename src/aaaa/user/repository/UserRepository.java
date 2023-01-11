package com.server.seb41_main_11.user.repository;

import com.server.seb41_main_11.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.email =: email")
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE u.userId =: userId")
    Optional<User> findById(long userId);
}
