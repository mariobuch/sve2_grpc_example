package at.sve2.services.grpc;


import at.sve2.services.grpc.BlogPostClientApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import at.sve2.services.grpc.model.PostProto;

import java.util.List;
import java.util.Scanner;

@Component
public class BlogPostConsoleApp implements CommandLineRunner {

    private final BlogPostClientApi blogPostClientApi;
    private final Scanner scanner;

    public BlogPostConsoleApp(BlogPostClientApi blogPostClientApi) {
        this.blogPostClientApi = blogPostClientApi;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) {
        System.out.println("===================================");
        System.out.println("  Blog Post Management System");
        System.out.println("===================================\n");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> findPostById();
                    case "2" -> findAllPosts();
                    case "3" -> addPost();
                    case "4" -> findAuthorById();
                    case "5" -> getAllAuthors();
                    case "6" -> createAuthor();
                    case "0" -> {
                        System.out.println("\nExiting application. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("\nInvalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                e.printStackTrace();
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Find Post by ID");
        System.out.println("2. Find All Posts");
        System.out.println("3. Add New Post");
        System.out.println("4. Find Author by ID");
        System.out.println("5. Get All Authors");
        System.out.println("6. Create New Author");
        System.out.println("0. Exit");
        System.out.println("================");
        System.out.print("Enter your choice: ");
    }

    private void findPostById() {
        System.out.print("\nEnter Post ID: ");
        String id = scanner.nextLine().trim();

        System.out.println("\nSearching for post with ID: " + id);
        PostProto.Post post = blogPostClientApi.findPostsById(id);

        if (post != null) {
            System.out.println("\n--- Post Found ---");
            System.out.println(post);
        } else {
            System.out.println("\nPost not found.");
        }
    }

    private void findAllPosts() {
        System.out.println("\nFetching all posts...");
        List<PostProto.Post> posts = blogPostClientApi.findAllPosts();

        if (posts != null && !posts.isEmpty()) {
            System.out.println("\n--- All Posts (" + posts.size() + ") ---");
            for (int i = 0; i < posts.size(); i++) {
                System.out.println("\n[" + (i + 1) + "]");
                System.out.println(posts.get(i));
            }
        } else {
            System.out.println("\nNo posts found.");
        }
    }

    private void addPost() {
        System.out.print("\nEnter Post Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter Post Text: ");
        String text = scanner.nextLine().trim();

        System.out.print("Enter Author ID: ");
        String authorId = scanner.nextLine().trim();

        System.out.println("\nCreating new post...");
        PostProto.Post newPost = blogPostClientApi.addPost(title, text, authorId);

        if (newPost != null) {
            System.out.println("\n--- Post Created Successfully ---");
            System.out.println(newPost);
        } else {
            System.out.println("\nFailed to create post.");
        }
    }

    private void findAuthorById() {
        System.out.print("\nEnter Author ID: ");
        String authorId = scanner.nextLine().trim();

        System.out.println("\nSearching for author with ID: " + authorId);
        PostProto.Author author = blogPostClientApi.findById(authorId);

        if (author != null) {
            System.out.println("\n--- Author Found ---");
            System.out.println(author);
        } else {
            System.out.println("\nAuthor not found.");
        }
    }

    private void getAllAuthors() {
        System.out.println("\nFetching all authors...");
        List<PostProto.Author> authors = blogPostClientApi.getAllAuthors();

        if (authors != null && !authors.isEmpty()) {
            System.out.println("\n--- All Authors (" + authors.size() + ") ---");
            for (int i = 0; i < authors.size(); i++) {
                System.out.println("\n[" + (i + 1) + "]");
                System.out.println(authors.get(i));
            }
        } else {
            System.out.println("\nNo authors found.");
        }
    }

    private void createAuthor() {
        System.out.print("\nEnter Author Name: ");
        String name = scanner.nextLine().trim();

        System.out.println("\nCreating new author...");
        PostProto.Author newAuthor = blogPostClientApi.createAuthor(name);

        if (newAuthor != null) {
            System.out.println("\n--- Author Created Successfully ---");
            System.out.println(newAuthor);
        } else {
            System.out.println("\nFailed to create author.");
        }
    }
}
