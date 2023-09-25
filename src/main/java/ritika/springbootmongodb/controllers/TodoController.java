package ritika.springbootmongodb.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodosDTO todo) {
       try {
        todo.setCreatedAt(new Date(System.currentTimeMillis()));
        todoRepo.save(todo);
        return new ResponseEntity<TodosDTO> (todo, HttpStatus.OK);
       } catch(Exception e) {
        return new ResponseEntity<> (e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
        Optional<TodosDTO> todoOptional=todoRepo.findById(id);
        if(todoOptional.isPresent()) {
            return new ResponseEntity<>(todoOptional.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found with id "+id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodosDTO todo) {
        Optional<TodosDTO> todoOptional=todoRepo.findById(id);
        if(todoOptional.isPresent()) {
            TodosDTO todoToSave=todoOptional.get();
            todoToSave.setCompleted(todo.getCompleted()!=null?todo.getCompleted():todoToSave.getCompleted());
            todoToSave.setTodo(todo.getTodo()!=null?todo.getTodo():todoToSave.getTodo());
            todoToSave.setDescription(todo.getDescription()!=null?todo.getDescription():todoToSave.getDescription());
            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoToSave);
            return new ResponseEntity<>(todoToSave,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo not found with id "+id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        try {
        
        todoRepo.deleteById(id);
        return new ResponseEntity<> ("Successfully deleted with id "+id, HttpStatus.OK);
       } catch(Exception e) {
        return new ResponseEntity<> (e.getMessage(), HttpStatus.NOT_FOUND);
       }
    }
}
