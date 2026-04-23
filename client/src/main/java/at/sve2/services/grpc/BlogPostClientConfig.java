package at.sve2.services.grpc;

import at.sve2.services.grpc.model.AuthorServiceGrpc;
import at.sve2.services.grpc.model.PostServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class BlogPostClientConfig {

    @Bean
    PostServiceGrpc.PostServiceBlockingStub postClient(GrpcChannelFactory channels) {
        return PostServiceGrpc.newBlockingStub(channels.createChannel("local"));
    }

    @Bean
    AuthorServiceGrpc.AuthorServiceBlockingStub authorClient(GrpcChannelFactory channels) {
        return AuthorServiceGrpc.newBlockingStub(channels.createChannel("local"));
    }

}
