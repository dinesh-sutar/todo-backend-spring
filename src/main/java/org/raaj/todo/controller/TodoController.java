package org.raaj.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.raaj.todo.model.Todo;
import org.raaj.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Todo Controller", description = "Handle crud for todo")
public class TodoController {

    private final TodoService todoService;

    // Create a new Todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    // Get all Todos
    @Operation(summary = "Get all todos")
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    // Update a Todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody Todo updatedTodo) {
        try{
            Todo todo = todoService.updateTodo(id,updatedTodo);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String id) {
        try{
            todoService.deleteTodoById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/state/{state}")
    public ResponseEntity<Todo> updateCompled(@PathVariable String id, @PathVariable boolean state){
        try{
            Todo todo =todoService.updateState(id,state);
            return ResponseEntity.ok(todo);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/all")
    public void deleteAll(){
        todoService.deleteAll();
    }
}
