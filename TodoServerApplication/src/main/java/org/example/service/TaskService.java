package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.TaskStatus;
import org.example.model.TaskDto;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskDto add(String title, String description, LocalDate dueDate) {
        var e = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO)
                .build();
        var saved = this.taskRepository.save(e);
        return entityToObject(saved);
    }

    public List<TaskDto> getAll() {
        return this.taskRepository.findAll().stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getByDueDate(String dueDate) {
        return this.taskRepository.findAllByDueDate(Date.valueOf(dueDate)).stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public List<TaskDto> getByStatus(TaskStatus status) {
        return this.taskRepository.findAllByStatus(status).stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public TaskDto getOne(Long id) {
        var entity = this.getById(id);
        return this.entityToObject(entity);
    }

    private  TaskEntity getById(Long id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("not exists task id [%d]", id)));
    }

    private TaskDto entityToObject(TaskEntity task) {
        return TaskDto.builder()
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
