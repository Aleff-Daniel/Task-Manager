package com.aleff.taskmanager.service;

import com.aleff.taskmanager.model.Task;
import com.aleff.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id){
        return taskRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Task save(Task task){
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus("TODO");
        return taskRepository.save(task);
    }

    public Task update(Long id, Task taskAtualizada){
        Task task = findById(id);
        task.setTitle(taskAtualizada.getTitle());
        task.setDescription(taskAtualizada.getDescription());
        task.setStatus(taskAtualizada.getStatus());
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
