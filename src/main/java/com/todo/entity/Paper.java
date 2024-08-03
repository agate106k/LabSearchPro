package com.todo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Table("papers")
public class Paper {

  @PrimaryKey
  @Column("paper_id")
  private int paperId;

  @Column("member_ids")
  private List<Integer> memberIds;

  @Column("conference_id")
  private int conferenceId;

  @Column("title")
  private String title;

  @Column("publication_date")
  private LocalDate publicationDate;

  @Column("cited_number")
  private int citedNumber;

  @Column("project_link")
  private String projectLink;

  @Column("related_fields")
  private String relatedFields;
}