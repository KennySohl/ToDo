package com.kennysohl.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<ToDo> saveToDo(@RequestBody ToDoData data){
        ToDo saved = toDoService.save(data.getText());
        if (saved == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> getToDos(){
        List<ToDo> toDos = toDoService.getToDos();
        return ResponseEntity.ok(toDos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable int id){
        boolean success = toDoService.delete(id);
        if(success)
            return ResponseEntity.ok("Deleted");
        else
            return ResponseEntity.status(500).build();
    }
}
