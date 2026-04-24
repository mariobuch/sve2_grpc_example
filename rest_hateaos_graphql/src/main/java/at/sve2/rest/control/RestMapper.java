package at.sve2.rest.control;

import at.sve2.rest.boundary.dto.Author;
import at.sve2.rest.boundary.dto.Post;
import at.sve2.rest.entity.AuthorEntity;
import at.sve2.rest.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class RestMapper {

    @Autowired
    ObjectMapper mapper;

    public Post map(PostEntity e) {
        return mapper.convertValue(e, Post.class);
    }

    public Author map(AuthorEntity e) {
        return mapper.convertValue(e, Author.class);
    }
}
