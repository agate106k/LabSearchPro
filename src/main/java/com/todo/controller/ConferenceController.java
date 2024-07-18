package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.todo.entity.Conference;
import com.todo.service.ConferenceService;
import java.util.NoSuchElementException;

import java.util.List;

@Controller
@RequestMapping("/conference")
public class ConferenceController {

  @Autowired
  private ConferenceService conferenceService;

  @GetMapping
  public String listConferences(Model model, @RequestParam(required = false) String search) {
    List<Conference> conferences;
    if (search != null && !search.isEmpty()) {
      conferences = conferenceService.searchByName(search);
    } else {
      conferences = conferenceService.findAll();
    }
    model.addAttribute("conferences", conferences);
    return "conference/list";
  }

  @GetMapping("/{id}")
  public String getConference(@PathVariable Long id, Model model) {
    Conference conference = conferenceService.findById(id).orElseThrow(() -> new NoSuchElementException("No conference found with id: " + id));
    model.addAttribute("conference", conference);
    return "conference/detail";
  }

  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("conference", new Conference());
    return "conference/create";
  }

  @PostMapping
  public String createConference(@ModelAttribute Conference conference) {
    conferenceService.save(conference);
    return "redirect:/conference";
  }
}