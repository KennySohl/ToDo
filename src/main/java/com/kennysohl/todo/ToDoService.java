package com.kennysohl.todo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoService {
    private final ToDoRepository repository;

    public ToDoService (ToDoRepository repository) {
        this.repository = repository;
    }

    public ToDo save(String text) {return repository.saveToDos(new ToDo(text));
    }

    public List<ToDo> getToDos(){
        return repository.getToDos();
    }

    public boolean delete(int id) {
        return repository.deleteToDos(id);
    }
}
