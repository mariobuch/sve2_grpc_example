package at.sve2.rest.boundary.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private String id;
    private String title;
    private String text;
    private String category;
    private String authorId;
}
