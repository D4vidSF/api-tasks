package tech.buildrun.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class ApiController {

    private Long idCounter = 1L;
    private List<Task> tasks = new ArrayList<>();

    //GET PARA BUSCAR TODOS OS RESULTADOS
    @GetMapping
    public List<Task> listTasks() {
        return tasks;
    }

    //GET PARA BUSCAR POR ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }

    //POST
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(idCounter++);
        tasks.add(task);
        return task;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTasks(){
        tasks = new ArrayList<>(); return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {

        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setName(updatedTask.getName());
                task.setDescription(updatedTask.getDescription());
                task.setStatus(updatedTask.getStatus());

                return ResponseEntity.ok(task);
            }
        }

        return ResponseEntity.notFound().build();
    }
}