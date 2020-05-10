package com.kennysohl.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToDoService {
    @Autowired
    ToDoRepository repository;

    public ToDo save(String text) {return repository.saveToDos(new ToDo(text));
    }

    public List<ToDo> getToDos(){
        return repository.getToDos();
    }

    public boolean delete(int id) {
        return repository.deleteToDos(id);
    }
}
