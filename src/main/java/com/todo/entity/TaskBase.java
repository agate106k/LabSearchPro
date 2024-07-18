package com.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Temporal;
import lombok.Data;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class TaskBase {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private LocalDateTime created_at;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at")
  private LocalDateTime updated_at;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "due_date")
  private LocalDateTime dueDate;
  

  @Column(name = "priority")
  private int priority;
  

  

}
