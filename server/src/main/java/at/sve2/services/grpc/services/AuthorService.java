package at.sve2.services.grpc.services;

import at.sve2.services.grpc.model.AuthorServiceGrpc;
import at.sve2.services.grpc.model.PostProto;
import at.sve2.services.grpc.model.PostProto.Author;
import at.sve2.services.grpc.repository.AuthorEntity;
import at.sve2.services.grpc.repository.FakeRepo;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class AuthorService extends AuthorServiceGrpc.AuthorServiceImplBase {

    private final FakeRepo repo;
    private final EntityMapper mapper;

    @Override
    public void findById(StringValue request, StreamObserver<Author> responseObserver) {
        System.out.println("++++++++++++++++ AuthorService::findById");

        var authorEntity = repo.getAuthorById(request.getValue());
        var author = mapper.map(authorEntity);

        responseObserver.onNext(author);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllAuthors(Empty request, StreamObserver<PostProto.Authors> responseObserver) {
        System.out.println("++++++++++++++++ AuthorService::getAllAuthors");

        var authorEntities = repo.getAllAuthors();
        var authors = mapper.map(authorEntities);

        responseObserver.onNext(authors);
        responseObserver.onCompleted();
    }


    @Override
    public void createAuthor(Author request, StreamObserver<Author> responseObserver) {
        System.out.println("++++++++++++++++ AuthorService::createAuthor");

        var authorEntity = new AuthorEntity(request.getId(), request.getName(), request.getThumbnail(), null);
        repo.saveAuthor(authorEntity);
        var author = mapper.map(authorEntity);

        responseObserver.onNext(author);
        responseObserver.onCompleted();
    }


}
