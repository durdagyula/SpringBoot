package unimiskolc.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import unimiskolc.springboot.model.User;
import unimiskolc.springboot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder  implements CommandLineRunner{
    private UserRepository userRepository;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<User> users = new ArrayList<>();

        users.add(new User("Gyula", 30, true, "admin"));
        users.add(new User("Maki", 29, false, "pass"));
        users.add(new User("Krizsu", 31, true, "pass"));
        users.add(new User("Tamás", 1, false, "pass"));
        users.add(new User("Levi", 27, false, "pass"));

        userRepository.save(users);
    }
}
