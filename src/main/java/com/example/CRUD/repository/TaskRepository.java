package com.example.CRUD.repository;

import com.example.CRUD.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

// Repository interface for CRUD operations on Task collection
public interface TaskRepository extends MongoRepository<Task,String> {

    // Find tasks by their severity level
    List<Task> findBySeverity(int severity);

    // Custom query to find tasks by assignee
    @Query("{ assignee: ?0 }")
    List<Task> getTasksByAssignee(String assignee);
}
