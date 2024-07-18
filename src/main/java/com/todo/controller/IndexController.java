package com.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.todo.service.UserService;

public class IndexController {
  public IndexController(UserService userService) {
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }
  
}
