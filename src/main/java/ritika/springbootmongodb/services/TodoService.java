package ritika.springbootmongodb.services;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolationException;
import ritika.springbootmongodb.exception.TodoCollectionException;
import ritika.springbootmongodb.models.TodosDTO;

@Service
public interface TodoService  {
    public void createTodo(TodosDTO todo) throws ConstraintViolationException, TodoCollectionException;
}
