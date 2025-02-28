package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.TaskStatus;
import org.example.model.TaskRequestDto;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskRequestDto add(String title, String description, String dueDate) {
        var e = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO)
                .build();
        var saved = this.taskRepository.save(e);
        return entityToObject(saved);
    }

    private TaskRequestDto entityToObject(TaskEntity task) {
        return TaskRequestDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .dueDate(task.getDueDate().toString())
                .createdAt(task.getCreatedAt().toLocalDateTime())
                .updatedAt(task.getUpdatedAt().toLocalDateTime())
                .build();
    }
}
