package com.todo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(name = "Users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class MUser {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "created_at")
  private String created_at;

  @Column(name = "updated_at")
  private String updated_at;

  @ManyToMany(mappedBy = "sharedUsers")
  private List<Task> sharedTasks = new ArrayList<Task>();

  @ManyToMany(mappedBy = "doneSharedUsers")
  private List<DoneTask> doneSharedTasks = new ArrayList<DoneTask>();

  @OneToMany(mappedBy = "createUser")
  private List<Task> myTasks = new ArrayList<Task>();

  @OneToMany(mappedBy = "createUser")
  private List<DoneTask> myDoneTasks = new ArrayList<DoneTask>();
}
