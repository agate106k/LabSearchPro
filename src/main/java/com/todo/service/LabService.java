package com.todo.service;

import com.todo.entity.Lab;
import com.todo.repository.LabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabService {

  @Autowired
  private LabRepository labRepository;

  public List<Lab> findAll() {
    return labRepository.findAll();
  }

  public Optional<Lab> findById(Long id) {
    return labRepository.findById(id);
  }

  public void save(Lab lab) {
    labRepository.save(lab);
  }
}