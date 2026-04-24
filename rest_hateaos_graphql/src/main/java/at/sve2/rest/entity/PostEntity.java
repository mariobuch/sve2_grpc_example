package at.sve2.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class PostEntity {

    private String id;
    private String title;
    private String text;
    private String category;
    private String authorId;

}
