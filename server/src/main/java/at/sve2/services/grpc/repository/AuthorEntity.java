package at.sve2.services.grpc.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {

    private String id;
    private String name;
    private String thumbnails;
    private List<PostEntity> posts;

}
