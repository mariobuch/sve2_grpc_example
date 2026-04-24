package at.sve2.hateoas.boundary;

import at.sve2.hateoas.boundary.model.Author;
import at.sve2.hateoas.boundary.model.Post;
import at.sve2.hateoas.control.HateoasAuthorService;
import at.sve2.hateoas.control.HateoasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/hateoas", produces = {"application/hal+json"})
public class HateoasAuthorController {

    @Autowired
    private HateoasAuthorService service;
    @Autowired
    private HateoasMapper mapper;


    @GetMapping(value = "/authors")
    public CollectionModel<Author> getAuthors() {

        var authors = service.getAuthors().stream().map(a -> mapper.map(a)).toList();
        for (final Author author : authors) {
            var selfLink = linkTo(methodOn(HateoasAuthorController.class).getPostsByAuthorId(author.getId())).withSelfRel();
            author.add(selfLink);
        }
        var link = linkTo(HateoasAuthorController.class).slash("authors").withSelfRel();
        return CollectionModel.of(authors, link);
    }

    @GetMapping(value = "/authors/{authorId}")
    public Author getAuthorById(@PathVariable String authorId) {
        var author = mapper.map(service.getAuthorById(authorId));
        author.add(linkTo(HateoasAuthorController.class).slash(author.getId()).withSelfRel());
        return author;
    }

    @GetMapping(value = "/authors/{authorId}/posts")
    public CollectionModel<Post> getPostsByAuthorId(@PathVariable final String authorId) {
        var posts = service.getPostsByAuthorId(authorId).stream().map(o -> mapper.map(o)).toList();
        for (final Post post : posts) {
            var selfLink = linkTo(methodOn(HateoasPostController.class).getPostById(post.getId())).withSelfRel();
            post.add(selfLink);
        }
        var link = linkTo(methodOn(HateoasAuthorController.class).getPostsByAuthorId(authorId)).withSelfRel();
        return CollectionModel.of(posts, link);
    }


}
