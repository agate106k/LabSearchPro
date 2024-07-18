package com.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByCreateUserId(int createUserId);
  List<Task> findByCreateUserIdOrderByDueDateAscPriorityDesc(int createUserId);
  @Query("select t from Task t join t.sharedUsers u where u.id = :id")
  List<Task> findTasksBySharedUserId(@Param("id") int id);


}
