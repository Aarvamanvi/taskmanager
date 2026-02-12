package com.veerendra.taskmanager.controller;

import com.veerendra.taskmanager.dto.TaskRequest;
import com.veerendra.taskmanager.model.Task;
import com.veerendra.taskmanager.service.TaskService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task createdTask = taskService.createTask(request);
        return ResponseEntity.status(201).body(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
public ResponseEntity<Task> updateTask(
        @PathVariable Long id,
        @Valid @RequestBody TaskRequest request) {

    Task updatedTask = taskService.updateTask(id, request);

    if (updatedTask == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedTask);
}

}
