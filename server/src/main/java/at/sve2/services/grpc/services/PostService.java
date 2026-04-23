package at.sve2.services.grpc.services;

import at.sve2.services.grpc.model.PostProto.Post;
import at.sve2.services.grpc.model.PostProto.Posts;
import at.sve2.services.grpc.model.PostServiceGrpc;
import at.sve2.services.grpc.repository.FakeRepo;
import at.sve2.services.grpc.repository.PostEntity;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class PostService extends PostServiceGrpc.PostServiceImplBase {

    private final FakeRepo repo;
    private final EntityMapper mapper;


    @Override
    public void findById(StringValue request, StreamObserver<Post> responseObserver) {
        System.out.println("++++++++++++++++ PostService::findById");

        var postEntity = repo.getPostById(request.getValue());
        var post = mapper.map(postEntity);

        responseObserver.onNext(post);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllPosts(Empty request, StreamObserver<Posts> responseObserver) {
        System.out.println("++++++++++++++++ PostService::getAllPosts");

        var postEntities = repo.getAllPosts();
        var posts = mapper.mapPosts(postEntities);

        responseObserver.onNext(posts);
        responseObserver.onCompleted();
    }

    @Override
    public void addPost(Post request, StreamObserver<Post> responseObserver) {
        System.out.println("++++++++++++++++ PostService::addPost");

        var postEntity = new PostEntity(request.getId(), request.getTitle(), request.getText(), request.getCategory().toString(), request.getAuthorId());
        repo.savePost(postEntity);
        var post = mapper.map(postEntity);

        responseObserver.onNext(post);
        responseObserver.onCompleted();
    }

}
