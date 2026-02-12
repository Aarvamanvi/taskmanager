package com.veerendra.taskmanager.service;

import com.veerendra.taskmanager.dto.TaskRequest;
import com.veerendra.taskmanager.model.Task;
import com.veerendra.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(TaskRequest request) {
        Task task = new Task(request.getTitle(), request.getDescription());
        return repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
}


    public Task updateTask(Long id, TaskRequest request) {
        return repository.findById(id)
                .map(task -> {
                    task.setTitle(request.getTitle());
                    task.setDescription(request.getDescription());
                    return repository.save(task);
                })
                .orElse(null);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
