package at.sve2.services.grpc;

import at.sve2.services.grpc.model.PostProto;

import java.util.List;

public interface BlogPostClientApi {
    PostProto.Post findPostsById(String id);
    List<PostProto.Post> findAllPosts();
    PostProto.Post addPost(String title, String text, String authorId);
    PostProto.Author findById(String authorId);
    List<PostProto.Author> getAllAuthors();
    PostProto.Author createAuthor(String name);
}
