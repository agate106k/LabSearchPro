package com.todo.repository.cassandra;

import com.todo.entity.Paper;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends CassandraRepository<Paper, Integer> {
}