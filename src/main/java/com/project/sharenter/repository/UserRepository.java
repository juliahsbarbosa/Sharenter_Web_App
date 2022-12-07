package com.project.sharenter.repository;

import com.project.sharenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Find user by Email or else throw exception
    Optional<User> findByEmail(String email);
}
