package at.sve2.graphql.boundary.model;

import at.sve2.graphql.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String id;
    private String name;
    private String thumbnails;
    private List<PostEntity> posts;
    private LocalDate birthday;

}

