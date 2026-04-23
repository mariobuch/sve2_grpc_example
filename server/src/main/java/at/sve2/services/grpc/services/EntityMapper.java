package at.sve2.services.grpc.services;


import at.sve2.services.grpc.model.PostProto;
import at.sve2.services.grpc.model.PostProto.Post.CategoryType;
import at.sve2.services.grpc.model.PostProto.Posts;
import at.sve2.services.grpc.repository.AuthorEntity;
import at.sve2.services.grpc.repository.PostEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityMapper {


    private String id;
    private String title;
    private String text;
    private String category;
    private String authorId;

    public PostProto.Post map(PostEntity e) {
        return PostProto.Post.newBuilder().setId(e.getId()).setTitle(e.getTitle()).setText(e.getText()).setCategory(CategoryType.valueOf(e.getCategory())).setAuthorId(e.getAuthorId()).build();
    }

    public PostProto.Author map(AuthorEntity e) {

        var postsBuilder = Posts.newBuilder();
        if (e.getPosts() != null) {
            for (PostEntity post : e.getPosts()) {
                postsBuilder.addPosts(map(post));
            }
        }
        return PostProto.Author.newBuilder().setId(e.getId()).setName(e.getName()).setThumbnail(e.getThumbnails()).setPosts(postsBuilder.build()).build();

    }

    public PostProto.Authors map(List<AuthorEntity> entities) {

        var authorsBuilder = PostProto.Authors.newBuilder();
        if (entities != null) {
            for (var i = 0; i < entities.size(); i++) {
                authorsBuilder.addAuthors(i, map(entities.get(i)));
            }
        }
        return authorsBuilder.build();
    }

    public PostProto.Posts mapPosts(List<PostEntity> entities) {

        var postsBuilder = PostProto.Posts.newBuilder();
        if (entities != null) {
            for (var i = 0; i < entities.size(); i++) {
                postsBuilder.addPosts(i, map(entities.get(i)));
            }
        }
        return postsBuilder.build();
    }


}
