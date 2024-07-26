package com.example.CRUD.service;

import com.example.CRUD.model.Task;
import com.example.CRUD.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    //CRUD CREATE, READ, UPDATE, DELETE

    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }

    public List<Task> findAllTasks() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            // Log the exception and rethrow it
            e.printStackTrace();
            throw new RuntimeException("Error retrieving tasks", e);
        }
    }

    public Task getTaskByTaskId(String taskId) {
        try {
            return repository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving task by ID", e);
        }
    }

    public List<Task> getTaskBySeverity(int severity) {
        try {
            return repository.findBySeverity(severity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving tasks by severity", e);
        }
    }

    public List<Task> getTaskByAssignee(String assignee) {
        try {
            return repository.getTasksByAssignee(assignee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving tasks by assignee", e);
        }
    }

    public Task updateTask(Task taskRequest) {
        try {
            Task existingTask = repository.findById(taskRequest.getTaskId()).orElseThrow(() -> new RuntimeException("Task not found"));
            existingTask.setDescription(taskRequest.getDescription());
            existingTask.setSeverity(taskRequest.getSeverity());
            existingTask.setAssignee(taskRequest.getAssignee());
            existingTask.setStoryPoint(taskRequest.getStoryPoint());
            return repository.save(existingTask);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating task", e);
        }
    }

    public String deleteTask(String taskId) {
        try {
            repository.deleteById(taskId);
            return taskId + " task deleted from dashboard";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting task", e);
        }
    }
}
