package com.todo.service;

import com.todo.entity.Paper;
import com.todo.repository.cassandra.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaperService {

  @Autowired
  private PaperRepository paperRepository;

  public List<Paper> findAll() {
    return paperRepository.findAll();
  }

  public Optional<Paper> findById(int id) {
    return paperRepository.findById(id);
  }

  public void save(Paper paper) {
    paperRepository.save(paper);
  }
}