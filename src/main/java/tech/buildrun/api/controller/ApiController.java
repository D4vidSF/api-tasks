package tech.buildrun.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.api.repository.TaskRepository;
import tech.buildrun.api.model.Task; // 👈 FALTA ISSO AQUI

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class ApiController {

    private final TaskRepository repository;

    public ApiController(TaskRepository repository) {
        this.repository = repository;
    }

    //GET buscar todas as tasks
    @GetMapping
    public List<Task> listTasks() {
        return repository.findAll();
    }

    // GET buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - criar task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    // PUT - atualizar task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {

        return repository.findById(id)
                .map(task -> {
                    task.setName(updatedTask.getName());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    return ResponseEntity.ok(repository.save(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - deletar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}