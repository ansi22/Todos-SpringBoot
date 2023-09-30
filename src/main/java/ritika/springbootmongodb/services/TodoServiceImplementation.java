package ritika.springbootmongodb.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolationException;
import ritika.springbootmongodb.exception.TodoCollectionException;
import ritika.springbootmongodb.models.TodosDTO;
import ritika.springbootmongodb.repository.TodoRepository;

@Service
public class TodoServiceImplementation implements TodoService {

    @Autowired
    private TodoRepository todoRepo;

    @Override
    public void createTodo(TodosDTO todo) throws ConstraintViolationException, TodoCollectionException {
        Optional<TodosDTO> todoOptional=todoRepo.findByTodo(todo.getTodo());
        System.out.println("todo"+todoOptional);
        if(todoOptional.isPresent()) {
            System.out.println("if"+todoOptional);
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        } else {
            System.out.println("el"+todoOptional);
            todo.setCreatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
        }
    }
}
