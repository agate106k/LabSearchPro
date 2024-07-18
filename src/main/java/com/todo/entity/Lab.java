package com.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Lab")
public class Lab {

  @Id
  @Column(name = "lab_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long labId;

  @Column(name = "name")
  private String name;

  @Column(name = "location")
  private String location;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  private String email;

  @Column(name = "website")
  private String website;

  @Column(name = "department")
  private String department;
}