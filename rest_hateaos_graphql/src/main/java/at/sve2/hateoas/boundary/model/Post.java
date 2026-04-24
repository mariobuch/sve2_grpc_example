package at.sve2.hateoas.boundary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends RepresentationModel<Post>{

    private String id;
    private String title;
    private String text;
    private String category;
    private String authorId;
}
