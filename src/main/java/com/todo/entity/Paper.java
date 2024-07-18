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
@Table(name = "Paper")
public class Paper {

  @Id
  @Column(name = "doi")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long doi;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Members member;

  @ManyToOne
  @JoinColumn(name = "conference_id")
  private Conference conference;

  @Column(name = "title")
  private String title;

  @Column(name = "publication_date")
  private String publicationDate;

  @Column(name = "cited_num")
  private int citedNum;

  @Column(name = "project_link")
  private String projectLink;

  @Column(name = "related_fields")
  private String relatedFields;
}