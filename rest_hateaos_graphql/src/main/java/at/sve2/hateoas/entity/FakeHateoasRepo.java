package at.sve2.hateoas.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeHateoasRepo {

    private final List<AuthorEntity> authors;
    private final List<PostEntity> posts;

    public FakeHateoasRepo() {

        var john = new AuthorEntity("a1", "John Doe", "none", null);
        var jane = new AuthorEntity("a2", "Jane Doe", "none", null);
        authors = List.of(john, jane);

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

    public PostEntity getPostById(String id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void savePost(PostEntity post) {

        this.posts.add(post);
    }

    public void saveAuthor(AuthorEntity author) {

        this.authors.add(author);
    }

}
