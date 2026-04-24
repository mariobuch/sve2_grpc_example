package at.sve2.rest.boundary;

import at.sve2.rest.boundary.dto.*;
import at.sve2.rest.control.RestMapper;
import at.sve2.rest.control.RestPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rest", produces = {"application/json"})
public class RestApiController {

    @Autowired
    private RestPostService service;

    @Autowired
    private RestMapper mapper;


    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") String id) {
        return service.getAuthorById(id).map(a -> ResponseEntity.ok().body(mapper.map(a))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok().body(service.getAuthors().stream().map(mapper::map).toList());
    }

    @GetMapping(path = "/posts")
    public ResponseEntity<List<Post>> getPosts(@RequestParam("authorId") String id) {
        return ResponseEntity.ok().body(service.getPostsForAuthor(id).stream().map(mapper::map).toList());
    }

}
