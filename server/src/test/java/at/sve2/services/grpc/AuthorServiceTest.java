package at.sve2.services.grpc;


import at.sve2.services.grpc.model.AuthorServiceGrpc;
import at.sve2.services.grpc.model.PostProto;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.grpc.test.autoconfigure.AutoConfigureInProcessTransport;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.client.GrpcChannelFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureInProcessTransport
public class AuthorServiceTest {

    @Autowired
    AuthorServiceGrpc.AuthorServiceBlockingStub service;

    @Test
    void createAuthorTest() {
        var author = PostProto.Author.newBuilder().setId("new-id").setName("Max Muster").setThumbnail("some-avatar-img").build();
        var created = service.createAuthor(author);
        assertNotNull(author);
        System.out.println("++++ test successful: "+ author);
    }

    @Test
    void findByAuthorIdTest() {
        var author = service.findById(StringValue.newBuilder().setValue("a1").build());
        assertNotNull(author);
        System.out.println("++++ test successful: "+ author);
    }

    @Test
    void getAllAuthorsTest() {
        var author = service.getAllAuthors(Empty.newBuilder().build());
        assertNotNull(author);
        System.out.println("++++ test successful: "+ author);
    }


    @TestConfiguration
    static class Config {

        @Bean
        AuthorServiceGrpc.AuthorServiceBlockingStub stub(GrpcChannelFactory channels) {
            return AuthorServiceGrpc.newBlockingStub(channels.createChannel("local"));
        }

    }
}
