package at.sve2.graphql.boundary;

import at.sve2.graphql.boundary.model.Author;
import at.sve2.graphql.boundary.model.Post;
import at.sve2.graphql.control.GraphqlAuthorService;
import at.sve2.graphql.control.GraphqlPostService;
import at.sve2.graphql.control.GraphqlModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GraphqlPostController {

    @Autowired
    private GraphqlPostService postService;
    @Autowired
    private GraphqlAuthorService authorService;
    @Autowired
    private GraphqlModelMapper mapper;


    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {

        return postService.recentPosts(count, offset).stream().map(mapper::map).toList();
    }

    @QueryMapping
    public List<Author> authors(@Argument String id) {

        return authorService.authors(id).stream().map(mapper::map).toList();
    }


    //batch fetching to mitigate the N+1 problem
    @BatchMapping(typeName = "Author", field = "posts")
    public List<List<Post>> posts(List<Author> authors) {

        System.out.println(">>>> BATCH fetching POSTS for author <<<<");
        var authorIds = authors.stream().map(Author::getId).toList();
        var posts4Authors = postService.postsForAutors(authorIds);
        return posts4Authors.stream().map(postList -> postList.stream().map(mapper::map).toList()).toList();
    }


   /* @SchemaMapping(typeName = "Author", field = "posts")
    public List<Post> posts(Author author) {

        System.out.println("+++++ fetching POSTS for author +++++++");
        final String authorId = (author != null) ? author.getId() : null;
        return postService.posts(authorId).stream().map(mapper::map).toList();
    }*/

    @SchemaMapping
    public Author author(Post post) {

        return mapper.map(authorService.getAuthorById(post.getAuthorId()));
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String text, @Argument String category, @Argument String authorId) {

        return mapper.map(postService.createPost(title, text, authorId, authorId));
    }

    @MutationMapping
    public Author createAuthor(@Argument String name, @Argument String thumbnail) {

        return mapper.map(authorService.createAuthor(name, thumbnail));
    }

}
