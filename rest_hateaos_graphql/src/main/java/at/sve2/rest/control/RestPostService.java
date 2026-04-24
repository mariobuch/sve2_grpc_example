package at.sve2.rest.control;

import at.sve2.rest.entity.FakeRestRepo;
import at.sve2.rest.entity.AuthorEntity;
import at.sve2.rest.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestPostService {

    @Autowired
    private FakeRestRepo repo;

    public Optional<AuthorEntity> getAuthorById(String id) {
        return Optional.ofNullable(repo.getAuthorById(id));
    }

    public List<AuthorEntity> getAuthors() {
        return repo.getAuthors();
    }

    public List<PostEntity> getPostsForAuthor(String id) {
        return repo.getPostsForAuthor(id);
    }
}
