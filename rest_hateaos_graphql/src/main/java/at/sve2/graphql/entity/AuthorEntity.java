package at.sve2.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {

    private String id;
    private String name;
    private String thumbnails;
    private List<PostEntity> posts;
    private LocalDate birthday;

}
