package com.syedsaifuddin045.tasks.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syedsaifuddin045.tasks.domain.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByTaskListId(UUID taskListId);

    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);

    void deleteByTaskListIdAndId(UUID taskListId, UUID taskId);
}
