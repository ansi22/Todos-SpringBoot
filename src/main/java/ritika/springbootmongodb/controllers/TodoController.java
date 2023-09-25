package ritika.springbootmongodb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ritika.springbootmongodb.models.TodosDTO;
import ritika.springbootmongodb.repository.TodoRepository;

@RestController
public class TodoController {
    
    @Autowired
    private TodoRepository todoRepo;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {
        List<TodosDTO> todos = todoRepo.findAll();
        if(todos.size()>0) {
            return new ResponseEntity<List<TodosDTO>> (todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
        }
    }
}
