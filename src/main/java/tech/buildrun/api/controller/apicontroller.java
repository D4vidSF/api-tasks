package tech.buildrun.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class apicontroller {

    private List<String> tasks = new ArrayList<>();

    private ObjectMapper objectMapper;

    public apicontroller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<String> listTasks() throws JsonProcessingException{
        return ResponseEntity.ok(objectMapper.writeValueAsString(tasks));
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody String task) {
        tasks.add(task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTasks(){
        tasks = new ArrayList<>();
        return ResponseEntity.ok().build();
    }
}