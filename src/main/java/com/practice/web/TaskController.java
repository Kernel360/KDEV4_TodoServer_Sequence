package com.practice.web;

import com.practice.model.dto.TaskDto;
import com.practice.model.vo.TaskRequestVo;
import com.practice.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 새로운 할 일 추가
     * @param req 추가하고자 하는 할 일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskRequestVo req) {
        var result = this.taskService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        return ResponseEntity.ok(result);
    }
}
