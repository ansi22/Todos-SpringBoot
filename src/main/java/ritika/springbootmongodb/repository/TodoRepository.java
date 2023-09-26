package ritika.springbootmongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import ritika.springbootmongodb.models.TodosDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodosDTO, String> {
    
    @Query("{'todo': ?0}")
    Optional<TodosDTO> findByTodo(String todo);
}
