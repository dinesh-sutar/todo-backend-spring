package org.raaj.todo.service;

import lombok.RequiredArgsConstructor;
import org.raaj.todo.model.Todo;
import org.raaj.todo.repository.TodoRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRespository respository;

    public Todo createTodo(Todo todo){
        return respository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return respository.findAll();
    }

    public Todo updateTodo(String id, Todo updatedTodo){
        if(!respository.existsById(id)){
            throw new IllegalArgumentException("Todo with id" + id + "does not exist");
        }
        updatedTodo.setId(id);
        return respository.save(updatedTodo);
    }

    public void deleteTodoById(String id){
        if(!respository.existsById(id)){
            throw new IllegalArgumentException("Todo with id" + id + "does not exist");
        }
        respository.deleteById(id);
    }

    public Todo updateState(String id, boolean state){
        Todo todo = respository.findById(id).orElseThrow();
        todo.setCompleted(state);
        respository.save(todo);
        return todo;
    }

    public void deleteAll(){
        respository.deleteAll();
    }
}
