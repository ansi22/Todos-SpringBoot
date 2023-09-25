package ritika.springbootmongodb.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")

public class TodosDTO {
    @Id 
    private String id;
    private String todo;
    private String description;
    private Boolean ompleted;
    private Date createdAt;
    private Date updatedAt; 
}
