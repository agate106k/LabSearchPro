package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.*;
import com.todo.entity.Lab;
import com.todo.service.LabService;

import java.util.List;

@Controller
@RequestMapping("/lab")
public class LabController {

  @Autowired
  private LabService labService;

  @GetMapping
  public String listLabs(Model model) {
    List<Lab> labs = labService.findAll();
    model.addAttribute("labs", labs);
    return "lab/list";
  }

  @GetMapping("/{id}")
  public String getLab(@PathVariable Long id, Model model) {
    Lab lab = labService.findById(id).orElseThrow(() -> new NoSuchElementException("No lab found with id: " + id));
    model.addAttribute("lab", lab);
    return "lab/detail";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("lab", new Lab());
    return "lab/create";
  }

  @PostMapping
  public String createLab(@ModelAttribute Lab lab) {
    labService.save(lab);
    return "redirect:/lab";
  }
}