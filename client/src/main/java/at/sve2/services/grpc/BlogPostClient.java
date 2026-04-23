package at.sve2.services.grpc;

import at.sve2.services.grpc.model.AuthorServiceGrpc;
import at.sve2.services.grpc.model.PostProto;
import at.sve2.services.grpc.model.PostServiceGrpc;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostClient implements BlogPostClientApi {


    @Autowired
    PostServiceGrpc.PostServiceBlockingStub grpcPostSrv;

    @Autowired
    AuthorServiceGrpc.AuthorServiceBlockingStub grpcAuthorSrv;

    @Override
    public PostProto.Post findPostsById(String id) {
        try {
            return grpcPostSrv.findById(StringValue.newBuilder().setValue(id).build());
        } catch (final StatusRuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<PostProto.Post> findAllPosts() {
        try {
            return grpcPostSrv.getAllPosts(Empty.newBuilder().build()).getPostsList();
        } catch (final StatusRuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public PostProto.Post addPost(String title, String text, String authorId) {
        try {
            var request = PostProto.Post.newBuilder().setId(UUID.randomUUID().toString()).setTitle(title).setText(text).setCategory(PostProto.Post.CategoryType.TECHNOLOGY).setAuthorId(authorId).build();
            return grpcPostSrv.addPost(request);
        } catch (final StatusRuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public PostProto.Author findById(String authorId) {

        try {
            return grpcAuthorSrv.findById(StringValue.newBuilder().setValue(authorId).build());
        } catch (final StatusRuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<PostProto.Author> getAllAuthors() {
        try {
            return grpcAuthorSrv.getAllAuthors(Empty.newBuilder().build()).getAuthorsList();
        } catch (final StatusRuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public PostProto.Author createAuthor(String name) {
        try {
            PostProto.Author request = PostProto.Author.newBuilder().setId(UUID.randomUUID().toString()).setName(name).setThumbnail("<undefined>").setPosts(PostProto.Posts.newBuilder().build()).build();
            return grpcAuthorSrv.createAuthor(request);
        } catch (final StatusRuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
