package com.todo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.repository.jpa.UserRepository; // 修正

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;

@Service
@Slf4j
public class TodoUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    assert (email != null);
    log.debug("loadUserByUsername(email):[{}]", email);

    if ("user".equals(email)) {
      return User.builder()
          .username("user")
          .password(passwordEncoder.encode("password"))
          .roles("USER")
          .build();
    } else {
      return userRepository.findByEmail(email)
          .map(user -> new org.springframework.security.core.userdetails.User(
              user.getEmail(),
              user.getPassword(),
              new ArrayList<>()))
          .orElseThrow(() -> new UsernameNotFoundException("User not found by email:[" + email + "]"));
    }
  }
}