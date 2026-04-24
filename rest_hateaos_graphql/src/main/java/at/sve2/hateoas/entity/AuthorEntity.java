package at.sve2.hateoas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class AuthorEntity {
    private String id;
    private String name;
    private String thumbnails;
    @ToString.Exclude
    private List<PostEntity> posts;

}
