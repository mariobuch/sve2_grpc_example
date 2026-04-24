package at.sve2.graphql.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FakeGraphqlRepo {

    private final List<AuthorEntity> authors;
    private final List<PostEntity> posts;

    public FakeGraphqlRepo() {

        authors = new ArrayList<AuthorEntity>();
        var john = new AuthorEntity("a1", "John Doe", "none", null, LocalDate.of(1990, 1, 1));
        var jane = new AuthorEntity("a2", "Jane Doe", "none", null, LocalDate.of(1991, 1, 1));
        authors.add(john);
        authors.add(jane);

        var post1 = new PostEntity("p1", "lemon chicken", "some recipie", "cooking", john.getId());
        var post2 = new PostEntity("p2", "schnitzel", "some recipie", "cooking", john.getId());
        var post3 = new PostEntity("p3", "how to graphql", "some demo code", "tech", jane.getId());
        posts = new ArrayList<PostEntity>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

    }

    public List<PostEntity> getRecentPosts(int count, int offset) {
        return posts.subList(offset, offset + count);
    }

    public List<PostEntity> getPostsForAuthor(String authorId) {
        return posts.stream().filter(post -> post.getAuthorId().equals(authorId)).toList();
    }

    public List<AuthorEntity> getAuthors() {
        return this.authors;
    }

    public AuthorEntity getAuthorById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public void savePost(PostEntity post) {
        this.posts.add(post);
    }

    public void saveAuthor(AuthorEntity author) {
        this.authors.add(author);
    }

}
