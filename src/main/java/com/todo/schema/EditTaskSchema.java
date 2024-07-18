package com.todo.schema;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class EditTaskSchema {
  private String title;
  private String description;
  private LocalDateTime due_date;
  private int priority;

  public EditTaskSchema(String title, String description, String due_date, int priority)
      throws ParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
    this.title = title;
    this.description = description;
    this.due_date = LocalDateTime.parse(due_date, formatter);
    this.priority = priority;
  }
}
