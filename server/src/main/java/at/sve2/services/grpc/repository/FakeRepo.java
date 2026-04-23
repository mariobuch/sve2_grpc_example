package at.sve2.services.grpc.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeRepo {

    private final List<AuthorEntity> authors;
    private final List<PostEntity> posts;

    public FakeRepo() {

        var john = new AuthorEntity("a1", "John Doe", "none", new ArrayList<PostEntity>());
        var jane = new AuthorEntity("a2", "Jane Doe", "none", new ArrayList<PostEntity>());
        authors = new ArrayList<AuthorEntity>();
        authors.add(john);
        authors.add(jane);

        var post1 = new PostEntity("p1", "lemon chicken", "some recipie", "COOKING", john.getId());
        var post2 = new PostEntity("p2", "latest influencer talk", "some gossip", "SOCIETY", john.getId());
        var post3 = new PostEntity("p3", "how to do grpc", "some demo code", "TECHNOLOGY", jane.getId());

        posts = new ArrayList<PostEntity>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        john.setPosts(List.of(post1, post2));
        jane.setPosts(List.of(post3));

    }

    public List<PostEntity> getAllPosts(){
        return posts;
    }

    public List<PostEntity> getRecentPosts(int count, int offset) {
        return posts.subList(offset, offset + count);
    }

    public List<PostEntity> getPostsForAuthor(String authorId) {
        return posts.stream().filter(post -> post.getAuthorId().equals(authorId)).toList();
    }

    public AuthorEntity getAuthorById(String id) {

        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public List<AuthorEntity> getAllAuthors() {
        return authors;
    }

    public PostEntity getPostById(String id) {

        return posts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }

    public void savePost(PostEntity post) {

        posts.add(post);
        authors.stream().filter(a -> a.getId().equals(post.getAuthorId())).map(author -> {
            author.getPosts().add(post);
            return author;
        });
    }

    public void saveAuthor(AuthorEntity author) {
        authors.add(author);
    }

}
