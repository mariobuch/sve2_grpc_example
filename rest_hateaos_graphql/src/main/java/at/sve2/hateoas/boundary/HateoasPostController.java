package at.sve2.hateoas.boundary;

import at.sve2.hateoas.boundary.model.Post;
import at.sve2.hateoas.control.HateoasPostService;
import at.sve2.hateoas.control.HateoasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/hateaos", produces = {"application/hal+json"})
public class HateoasPostController {

    @Autowired
    private HateoasPostService service;

    @Autowired
    private HateoasMapper mapper;

    @GetMapping(value = "/posts/{postId}")
    public Post getPostById(@PathVariable String postId) {
        var post = mapper.map(service.getPostById(postId));
        post.add(linkTo(HateoasPostController.class).slash(post.getId()).withSelfRel());
        return post;
    }

}