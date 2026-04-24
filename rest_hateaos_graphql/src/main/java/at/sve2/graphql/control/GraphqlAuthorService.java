package at.sve2.graphql.control;

import at.sve2.graphql.entity.AuthorEntity;
import at.sve2.graphql.entity.FakeGraphqlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GraphqlAuthorService {

    @Autowired
    private FakeGraphqlRepo repo;

    public List<AuthorEntity> authors(@Argument String id) {
        if (id == null) {
            return repo.getAuthors();
        } else {
            return List.of(repo.getAuthorById(id));
        }
    }

    public AuthorEntity getAuthorById(String id) {
        return repo.getAuthorById(id);
    }

    public AuthorEntity createAuthor(String name, String thumbnail) {
        var a = new AuthorEntity(UUID.randomUUID().toString(), name, thumbnail, null, null);
        repo.saveAuthor(a);
        return a;
    }

}
