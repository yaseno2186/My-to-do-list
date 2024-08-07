package dev.Yass.to_do_list.controller;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;


import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


import dev.Yass.to_do_list.model.task;
import dev.Yass.to_do_list.service.TodoService;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Autowired
    private ObjectMapper objectMapper;

    private task testTask;

    @BeforeEach
    void setUp() {
        testTask = new task();
        testTask.setId(1L);
        testTask.setTitle("Test Task");
        testTask.setDescription("Test Description");
    }

    @Test
    void testGetAllTodos() throws Exception {
        when(todoService.findAll()).thenReturn(Arrays.asList(testTask));

        mockMvc.perform(get("/api/task/all"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[0].title").value("Test Task"));
    }

    @Test
    void testGetTodoById() throws Exception {
        when(todoService.findById(1L)).thenReturn(Optional.of(testTask));

        mockMvc.perform(get("/api/task/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void testGetTodoByIdNotFound() throws Exception {
        when(todoService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/task/1"))
               .andExpect(status().isNotFound());
    }

    @Test
    void testCreateTodo() throws Exception {
        when(todoService.save(any(task.class))).thenReturn(testTask);

        mockMvc.perform(post("/api/task/create")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(testTask)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void createTodo_BlankTitle_ThrowsBadRequestException() {
        // Arrange
        task inputTask = new task();
        inputTask.setTitle("");
        inputTask.setDescription("Test Description");

        // Act & Assert
        assertThrows(BadRequestException.class, () -> todoController.createTodo(inputTask));
        verify(todoService, never()).save(any());
    }

    @Test
    void testUpdateTodo() throws Exception {
        task updatedTask = new task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");

        when(todoService.findById(1L)).thenReturn(Optional.of(testTask));
        when(todoService.save(any(task.class))).thenReturn(updatedTask);

        mockMvc.perform(put("/api/task/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(updatedTask)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.title").value("Updated Task"))
               .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    void testUpdateTodoNotFound() throws Exception {
        when(todoService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/task/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(testTask)))
               .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTodoById() throws Exception {
        when(todoService.findById(1L)).thenReturn(Optional.of(testTask));

        mockMvc.perform(delete("/api/task/1"))
               .andExpect(status().isOk());

        verify(todoService, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTodoByIdNotFound() throws Exception {
        when(todoService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/task/1"))
               .andExpect(status().isNotFound());

        verify(todoService, never()).deleteById(anyLong());
    }
}
