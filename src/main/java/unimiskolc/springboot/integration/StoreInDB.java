package unimiskolc.springboot.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unimiskolc.springboot.model.User;
import unimiskolc.springboot.repository.UserRepository;

import java.util.List;

@Component
public class StoreInDB {

    @Autowired
    private UserRepository userRepository;

            public List<User> save( List<User> user) {
                return userRepository.save(user);
            }

}
