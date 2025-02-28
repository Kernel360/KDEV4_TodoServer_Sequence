package org.example.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.TaskStatus;
import org.example.model.TaskDto;
import org.example.service.TaskService;
import org.example.web.vo.TaskRequestVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskRequestVo req) {
        var result = this.taskService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        this.taskService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 마감일에 해당하는 할일 목록을 반환
     * @param dueDate 할일의 마감일
     * @return 마감일에 해당하는 할일 목록
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> getTaskList(Optional<String> dueDate) {
        List<TaskDto> result;

        if(dueDate.isPresent()) {
            result = this.taskService.getByDueDate(dueDate.get());
        } else {
            result = this.taskService.getAll();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 id에 해당하는 할일 한개를 조회
     * @param id 할일의 id
     * @return id에 해당하는 할일 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        var result = this.taskService.getOne(id);
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 상태에 해당하는 할일 목록을 반환
     * @param status 할일의 상태
     * @return 상태에 해당하는 할일 목록
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDto>> getByStatus(@PathVariable TaskStatus status) {
        var result = this.taskService.getByStatus(status);
        return ResponseEntity.ok(result);
    }

    /**
     * 특정 id에 해당하는 할일 수정
     * @param id
     * @param task 수정할 할일 정보
     * @return 수정된 할일 객체
     */
//    @PutMapping("/{id}")
//    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @ResponseBody TaskRequestVo task) {
//        var result = this.taskService.update(id,
//                task.getTitle(),
//                task.getDescription(),
//                task.getDueDate());
//        return ResponseEntity.ok(result);
//    }
}
