package at.sve2.services.grpc;

import at.sve2.services.grpc.model.AuthorServiceGrpc;
import at.sve2.services.grpc.model.PostProto;
import at.sve2.services.grpc.model.PostServiceGrpc;
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
public class PostServiceTest {

    @Autowired
    PostServiceGrpc.PostServiceBlockingStub service;

    @Test
    void addPostTest() {

        var request = PostProto.Post.newBuilder().setId("new-post-id").setTitle("some title").setText("posting text").setCategory(PostProto.Post.CategoryType.TECHNOLOGY).setAuthorId("a1").build();

        var result = service.addPost(request);

        assertNotNull(result);
        System.out.println("++++ test successful: " + result);
    }

    @Test
    void findByPostIdTest() {

        var result = service.findById(StringValue.newBuilder().setValue("p1").build());

        assertNotNull(result);
        System.out.println("++++ test successful: " + result);
    }

    @Test
    void getAllPostsTest() {

        var result = service.getAllPosts(Empty.newBuilder().build());

        assertNotNull(result);
        System.out.println("++++ test successful: " + result);
    }


    @TestConfiguration
    static class Config {

        @Bean
        PostServiceGrpc.PostServiceBlockingStub stub(GrpcChannelFactory channels) {
            return PostServiceGrpc.newBlockingStub(channels.createChannel("local"));
        }

    }
}
