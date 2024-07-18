package com.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "DoneTasks")
public class DoneTask extends TaskBase {
  @ManyToOne
  private MUser createUser = new MUser();

  @ManyToMany
  private List<MUser> doneSharedUsers = new ArrayList<MUser>();
}
