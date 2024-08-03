package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.todo.entity.Paper;
import com.todo.service.PaperService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/paper")
public class PaperController {

  @Autowired
  private PaperService paperService;

  @GetMapping
  public String listPapers(Model model) {
    List<Paper> papers = paperService.findAll();
    model.addAttribute("papers", papers);
    return "paper/list";
  }

  @GetMapping("/{id}")
  public String getPaper(@PathVariable int id, Model model) {
    Paper paper = paperService.findById(id).orElseThrow(() -> new NoSuchElementException("No paper found with id: " + id));
    model.addAttribute("paper", paper);
    return "paper/detail";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("paper", new Paper());
    return "paper/create";
  }

  @PostMapping
  public String createPaper(@ModelAttribute Paper paper) {
    paperService.save(paper);
    return "redirect:/paper";
  }
}