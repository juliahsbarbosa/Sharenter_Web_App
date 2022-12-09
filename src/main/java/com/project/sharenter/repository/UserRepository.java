package com.project.sharenter.repository;

import com.project.sharenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional find user by Email
    Optional<User> findByEmail(String email);
}
