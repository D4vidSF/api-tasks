package tech.buildrun.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}