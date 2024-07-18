package com.todo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import com.todo.entity.DoneTask;
import com.todo.service.DoneTaskService;
import com.todo.entity.Task;
import com.todo.service.TaskService;
import com.todo.service.UserService;
import com.todo.entity.MUser;

import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;
import java.security.Principal;
import java.time.LocalDateTime;

import lombok.Data;

import com.todo.schema.EditTaskSchema;

@Controller
public class DoneTaskcontroller {
  @Autowired
  private DoneTaskService doneTaskService;

  @Autowired
  private TaskService taskService;

  @Autowired
  private UserService userService;

  @GetMapping("/all-donetask")
  public List<DoneTask> tasks() {
    return doneTaskService.findAll();
  }

  @GetMapping("/donetask")
  public String donetask(Model model, Principal principal) {
    String email = principal.getName();

    Optional<MUser> optUser = userService.findByEmail(email);

    if (optUser.isPresent()) {
      MUser user = optUser.get();
      List<DoneTask> tasks = doneTaskService.findByCreateUserId(user.getId());
      // findByCreateUserId(user.getId());
      model.addAttribute("doneTasks", tasks);
      return "/task/user-top-done";
    } else {
      return "redirect:/error";
    }
  }

  @Data
  public class CreateTaskResponse {
    private long id;
  }

  @PutMapping("/donetask/{id}")
  public String update(@PathVariable("id") Long id, @Validated @ModelAttribute DoneTask task,
      BindingResult bindingResult) {
    var searched = doneTaskService.findById(id);
    if (searched.isEmpty()) {
      throw new TaskNotFoundException();
    }
    DoneTask searchedTask = searched.get();
    task.setId(id);
    task.setCreated_at(searchedTask.getCreated_at());
    doneTaskService.update(task);
    return "redirect:/donetask/" + id;
  }
  @GetMapping("/donetask/{id}")
  public ModelAndView task(@PathVariable("id") Long id) {
    ModelAndView mav = new ModelAndView("task/doneTaskDetail");
    DoneTask task = doneTaskService.findById(id).orElseThrow(() -> new NoSuchElementException("No task found with id: " + id));
    mav.addObject("task", task);
    mav.addObject("sharedUsers", task.getDoneSharedUsers());

    return mav;
  }
  @RequestMapping(value = "/donetask/{id}", method = RequestMethod.PUT, consumes = "application/json")
  public Boolean update(@RequestBody EditTaskSchema req, @PathVariable("id") Long id) {
    var searched = doneTaskService.findById(id);
    if (searched.isEmpty()) {
      return false;
    }
    var task = searched.get();
    task.setTitle(req.getTitle());
    task.setDescription(req.getDescription());
    task.setDueDate(req.getDue_date());
    task.setPriority(req.getPriority());
    task.setUpdated_at(LocalDateTime.now());

    doneTaskService.save(task);
    return true;
  }

  @DeleteMapping("/donetask/{id}")
  public String delete(@PathVariable("id") Long id) {
    doneTaskService.delete(id);
    return "redirect:/donetask";
  }

  @PostMapping("/donetask/{id}/undone")
  public String undone(@PathVariable("id") Long id) {
    var searched = doneTaskService.findById(id);
    if (searched.isEmpty()) {
      return "redirect:/task";
    }
    var donetask = searched.get();
    donetask.setUpdated_at(LocalDateTime.now());

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

    doneTaskService.delete(id);
    taskService.save(task);
    return "redirect:/donetask";
  }
  
}
