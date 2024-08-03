package com.todo.service;

import com.todo.controller.SignupForm;
import com.todo.entity.MUser;
import com.todo.entity.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.repository.jpa.UserRepository; // 修正

import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public void setUser(SignupForm form) {
    MUser user = new MUser();
    user.setName(form.getName());
    user.setEmail(form.getEmail());
    // パスワードの暗号化
    String rawPassword = form.getPassword();
    user.setPassword(encoder.encode(rawPassword));
    String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    user.setCreated_at(currentDateTime);
    user.setUpdated_at(currentDateTime);
    userRepository.save(user);
  }

  public Optional<MUser> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<MUser> findById(long id) {
    return userRepository.findById(id);
  }

  public List<MUser> findAll() {
    return userRepository.findAll();
  }

  public static void setUser(@NonNull String name, @NonNull String email, @NonNull String password) {
  }
}
