package com.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.todo.service.TaskService;
import com.todo.service.UserService;
import com.todo.entity.Task;
import com.todo.entity.DoneTask;
import com.todo.entity.MUser;
import com.todo.service.DoneTaskService;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.Data;

@Controller
public class SharedDoneController {
  @Autowired
  private TaskService taskService;

  @Autowired
  private DoneTaskService doneTaskService;

  @Autowired
  private UserService userService;

  @Data
  public class CreateTaskResponse {
    private long id;
  }

  @GetMapping("/sharedDone")
  public ModelAndView shared(@AuthenticationPrincipal UserDetails user) {
    ModelAndView mav = new ModelAndView("task/sharedDone");

    var loginUser = userService.findByEmail(user.getUsername())
        .orElseThrow(() -> new NoSuchElementException("No user found with email: " + user.getUsername()));

    List<DoneTask> tasks = loginUser.getDoneSharedTasks();
    mav.addObject("tasks", tasks);
    return mav;
  }

  @GetMapping("/sharedDone/{id}")
  public ModelAndView task(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails user) {
    var loginUser = userService.findByEmail(user.getUsername())
        .orElseThrow(() -> new NoSuchElementException("No user found with email: " + user.getUsername()));
    var isShared = loginUser.getDoneSharedTasks().stream().anyMatch(task -> task.getId().equals(id));

    if (!isShared) {
      throw new NoSuchElementException("No task found with id: " + id);
    }

    ModelAndView mav = new ModelAndView("task/sharedDoneDetail");
    DoneTask task = doneTaskService.findById(id)
        .orElseThrow(() -> new NoSuchElementException("No task found with id: " + id));
    mav.addObject("task", task);
    mav.addObject("sharedUsers", task.getDoneSharedUsers());

    return mav;
  }
  @PostMapping("/sharedDone/{id}/undone")
  public String done(@PathVariable("id") Long id) {
    var searched = doneTaskService.findById(id);
    if (searched.isEmpty()) {
      return "redirect:/shared";
    }
    DoneTask donetask = searched.get();
    var task = new Task();
    task.setTitle(donetask.getTitle());
    task.setDescription(donetask.getDescription());
    task.setDueDate(donetask.getDueDate());
    task.setPriority(donetask.getPriority());
    task.setCreated_at(donetask.getCreated_at());
    task.setUpdated_at(donetask.getUpdated_at());
    task.setCreateUser(donetask.getCreateUser());
    task.setSharedUsers(donetask.getDoneSharedUsers().stream().map(i -> {
      MUser user = new MUser();
      BeanUtils.copyProperties(i, user);
      return user;
    }).toList());
    taskService.save(task);
    doneTaskService.delete(id);
    return "redirect:/sharedDone";
  }
}
