package at.sve2.rest.entity;

import at.sve2.hateoas.entity.PostEntity;
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
