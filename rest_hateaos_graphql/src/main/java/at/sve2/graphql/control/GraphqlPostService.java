package at.sve2.graphql.control;

import at.sve2.graphql.entity.AuthorEntity;
import at.sve2.graphql.entity.FakeGraphqlRepo;
import at.sve2.graphql.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class GraphqlPostService {

    @Autowired
    private FakeGraphqlRepo repo;

    public List<PostEntity> recentPosts(int count, int offset) {
        return repo.getRecentPosts(count, offset);
    }

    public List<PostEntity> posts(String authorId) {
        if (authorId != null) {
            return repo.getPostsForAuthor(authorId);
        } else {
            return Collections.emptyList();
        }
    }

    public List<List<PostEntity>> postsForAutors(List<String> authorIds){
        return authorIds.stream().map(authorId -> repo.getPostsForAuthor(authorId)).toList();
    }

    public PostEntity createPost(String title, String text,
                                 String category, String authorId) {

        PostEntity post = new PostEntity(UUID.randomUUID().toString(), title, text, category, authorId);
        repo.savePost(post);
        return post;
    }

}
