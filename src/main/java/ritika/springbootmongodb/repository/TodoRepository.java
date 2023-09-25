package ritika.springbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ritika.springbootmongodb.models.TodosDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodosDTO, String> {
    
}
