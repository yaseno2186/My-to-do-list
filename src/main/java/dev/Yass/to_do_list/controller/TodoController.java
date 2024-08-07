package dev.Yass.to_do_list.controller;

import dev.Yass.to_do_list.model.task;
import dev.Yass.to_do_list.service.TodoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/task")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
        public List<task> getAllTodos() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<task> getTodoById(@PathVariable Long id) {
        return todoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public task createTodo(@RequestBody task task) throws BadRequestException {
        if (!task.getTitle().isBlank()) {
            return todoService.save(task);
        }
        throw new BadRequestException("Title cannot be blank");
    }

    @PutMapping("/{id}")
    public ResponseEntity<task> updateTodo(@PathVariable Long id, @RequestBody task todoDetails) {
        return todoService.findById(id)
                .map(task -> {
                    task.setTitle(todoDetails.getTitle());
                    task.setDescription(todoDetails.getDescription());
                    task.setDone(todoDetails.isDone());
                    task.setReminderDate(todoDetails.getReminderDate());
                    task.setRepeat(todoDetails.isRepeat());
                    task.setArchived(todoDetails.isArchived());
                    task.setDeleted(todoDetails.isDeleted());
                    return ResponseEntity.ok(todoService.save(task));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        if (todoService.findById(id).isPresent()) {
            todoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
