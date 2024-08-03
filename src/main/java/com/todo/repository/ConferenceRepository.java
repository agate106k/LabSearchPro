package com.todo.repository.jpa;

import com.todo.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
  List<Conference> findByNameContainingIgnoreCase(String name);
}