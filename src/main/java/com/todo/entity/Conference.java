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
@Table(name = "Conference")
public class Conference {

  @Id
  @Column(name = "conference_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long conferenceId;

  @Column(name = "name")
  private String name;

  @Column(name = "start_date")
  private String startDate;

  @Column(name = "end_date")
  private String endDate;

  @Column(name = "location")
  private String location;

  @Column(name = "organizers")
  private String organizers;

  @Column(name = "website")
  private String website;
}