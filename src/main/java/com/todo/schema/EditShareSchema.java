package com.todo.schema;

import lombok.Data;

@Data
public class EditShareSchema {
  private long userId;
  private long taskId;
}