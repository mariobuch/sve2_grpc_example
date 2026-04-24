package at.sve2.hateoas.control;

import at.sve2.hateoas.entity.AuthorEntity;
import at.sve2.hateoas.entity.FakeHateoasRepo;
import at.sve2.hateoas.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HateoasAuthorService {

    @Autowired
    private FakeHateoasRepo repo;

    public List<AuthorEntity> getAuthors() {
        return repo.getAuthors();
    }

    public AuthorEntity getAuthorById(String id) {
        return repo.getAuthorById(id);
    }

    public PostEntity getPostByAuthorId(String authorId, String postId) {
        //just a dummy impl
        return repo.getPostById(postId);
    }

    public List<PostEntity> getPostsByAuthorId(String authorId) {
        return repo.getPostsForAuthor(authorId);
    }

}
