package at.sve2.hateoas.control;

import at.sve2.hateoas.boundary.model.Author;
import at.sve2.hateoas.boundary.model.Post;
import at.sve2.hateoas.entity.AuthorEntity;
import at.sve2.hateoas.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class HateoasMapper {

    @Autowired
    ObjectMapper mapper;

    public Post map(PostEntity e) {
        return mapper.convertValue(e, Post.class);
    }

    public Author map(AuthorEntity e) {
        return mapper.convertValue(e, Author.class);
    }
}

