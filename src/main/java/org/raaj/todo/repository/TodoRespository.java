package org.raaj.todo.repository;

import org.raaj.todo.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRespository extends MongoRepository<Todo, String > {
}
