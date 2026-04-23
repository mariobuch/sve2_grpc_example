package at.sve2.services.grpc;

import at.sve2.services.grpc.repository.FakeRepo;
import at.sve2.services.grpc.services.AuthorService;
import at.sve2.services.grpc.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class App {

    @Autowired
    FakeRepo repo;
    @Autowired
    private FakeRepo fakeRepo;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {

        System.out.println("+++++++++++++++ GRPC server ready ++++++++++++++");
        System.out.println("-list of authors---");
        fakeRepo.getAllAuthors().stream().forEach(a -> System.out.println(" "+a.getId()  +": "+a.getName()));
        System.out.println("-list of posts-----");
        fakeRepo.getAllPosts().stream().forEach(p -> System.out.println(" "+p.getId()  +": "+p.getTitle()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
    }

}
