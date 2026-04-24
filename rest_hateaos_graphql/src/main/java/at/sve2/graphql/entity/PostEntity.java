package at.sve2.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

    private String id;
    private String title;
    private String text;
    private String category;
    private String authorId;

}
