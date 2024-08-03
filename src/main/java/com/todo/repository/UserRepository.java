package com.todo.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.todo.entity.MUser;

@Repository
public interface UserRepository extends JpaRepository<MUser, Long> {
    Optional<MUser> findByEmail(String email);
}