package dev.Yass.to_do_list.service;

import dev.Yass.to_do_list.model.task;
import dev.Yass.to_do_list.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    // FINDS:
    public Optional<task> findById(Long id) {
        return todoRepository.findById(id);
    }

    public task findByTitle(String title) {
        return (task) todoRepository.findByTitle(title);
    }

    public List<task> findAll() {
        return todoRepository.findAll();
    }

    // INSERTS:
    public task save(task task) {
        return todoRepository.save(task);
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteByTitle(String title) {
        todoRepository.deleteByTitle(title);
    }


}