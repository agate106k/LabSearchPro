package com.todo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.entity.Task;
import com.todo.repository.TaskRepository;

import java.util.Optional;

@Service
public class TaskService {
  @Autowired
  private TaskRepository taskRepository;

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public Optional<Task> findById(Long id) {
    return taskRepository.findById(id);
  }

  public Long save(Task task) {
    return taskRepository.save(task).getId();
  }

  public void delete(Long id) {
    taskRepository.deleteById(id);
  }

  public Task update(Task task) {
    if (task.getId() == null) {
        throw new IllegalArgumentException("ID cannot be null when updating a task.");
    }
    LocalDateTime currentDateTime = LocalDateTime.now();
    
    task.setUpdated_at(currentDateTime);
    return taskRepository.save(task);
}

public List<Task> findByCreateUserId(int id) {
  return taskRepository.findByCreateUserIdOrderByDueDateAscPriorityDesc(id);
}

public List<Task> findBySharedUserId(int id) {
  return taskRepository.findTasksBySharedUserId(id);
}




}
