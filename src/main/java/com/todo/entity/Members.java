package com.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Members")
public class Members {

  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long memberId;

  @ManyToOne
  @JoinColumn(name = "lab_id")
  private Lab lab;

  @Column(name = "name")
  private String name;

  @Column(name = "grade")
  private String grade;

  @Column(name = "birth")
  private String birth;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "role")
  private String role;

  @Column(name = "github")
  private String github;

  @Column(name = "photo")
  private String photo;
}