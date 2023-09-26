package ritika.springbootmongodb.services;

import jakarta.validation.ConstraintViolationException;
import ritika.springbootmongodb.exception.TodoCollectionException;
import ritika.springbootmongodb.models.TodosDTO;

public interface TodoService  {
     public void createTodo(TodosDTO todo) throws ConstraintViolationException, TodoCollectionException;
}
