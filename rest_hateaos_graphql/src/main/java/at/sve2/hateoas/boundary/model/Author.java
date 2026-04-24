package at.sve2.hateoas.boundary.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author extends RepresentationModel<Author> {

    private String id;
    private String name;
    private String thumbnails;

}
