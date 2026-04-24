package at.sve2.hateoas.control;

import at.sve2.hateoas.entity.FakeHateoasRepo;
import at.sve2.hateoas.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HateoasPostService {

    @Autowired
    private FakeHateoasRepo repo;

    public PostEntity getPostById(String id){
        return repo.getPostById(id);
    }

}
