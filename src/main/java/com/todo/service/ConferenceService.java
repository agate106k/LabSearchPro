package com.todo.service;

import com.todo.entity.Conference;
import com.todo.repository.jpa.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConferenceService {

  @Autowired
  private ConferenceRepository conferenceRepository;

  public List<Conference> findAll() {
    return conferenceRepository.findAll();
  }

  public Optional<Conference> findById(Long id) {
    return conferenceRepository.findById(id);
  }

  public void save(Conference conference) {
    conferenceRepository.save(conference);
  }

  // 検索機能の追加
  public List<Conference> searchByName(String name) {
    return conferenceRepository.findByNameContainingIgnoreCase(name);
  }
}