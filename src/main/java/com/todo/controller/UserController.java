package com.todo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.todo.entity.MUser;
import com.todo.entity.Task;
import com.todo.service.TaskService;
import com.todo.service.UserService;
import com.todo.auth.SimpleLoginUser;

@Controller
@RequestMapping("/")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private TaskService taskService;

  @GetMapping("/signup")
  public String getSignup(@ModelAttribute("user") SignupForm form) {
    return "user/signup";
  }

  @PostMapping("/signup")
  public String signup(@Validated @ModelAttribute("user") SignupForm form, BindingResult result,
      RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      userService.setUser(form);
      return "user/signup";
    }

    userService.setUser(form);

    redirectAttributes.addFlashAttribute("successMessage", "アカウントの登録が完了しました");
    return "redirect:/";
  }

  @GetMapping("/index")
  public String getIndex(Model model, @AuthenticationPrincipal SimpleLoginUser loginUser) {
    List<Task> tasks = taskService.findAll();
    model.addAttribute("tasks", tasks);
    return "index";
  }

  @RequestMapping("/login")
  public String loginForm() {
    return "user/login";
  }

  @GetMapping("/task")
  public String user(Model model, Principal principal) {
    // ログインユーザーのemailを取得
    String email = principal.getName();

    // ログインユーザーの情報を取得
    Optional<MUser> optUser = userService.findByEmail(email);

    if (optUser.isPresent()) {
      MUser user = optUser.get();

      // ログインユーザーが作成したタスクのみを取得し、due_dateの昇順、priorityの降順で並べる
      List<Task> tasks = taskService.findByCreateUserId(user.getId());

      model.addAttribute("tasks", tasks);
      return "task/user-top";
    } else {
      return "redirect:/error";
    }
  }

}