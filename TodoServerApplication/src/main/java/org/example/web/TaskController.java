package org.example.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.TaskRequestDto;
import org.example.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /** Java Doc
     * 새로운 할일 추가
     * @param req 추가하고자 하는 할일
     * @return 추가된 할일
     */

    @PostMapping
    public ResponseEntity<TaskRequestDto> createTask(@RequestBody TaskRequestDto req) {
        var result = this.taskService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        this.taskService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        return ResponseEntity.ok(result);
    }
}
