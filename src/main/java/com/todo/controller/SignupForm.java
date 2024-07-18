package com.todo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {
  @NonNull
  private String name;
  @NonNull
  private String email;
  @NonNull
  private String password;
}
