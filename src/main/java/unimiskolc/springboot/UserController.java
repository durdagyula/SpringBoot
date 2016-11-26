package unimiskolc.springboot;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private List<User> users;

    public UserController(){
        users = new ArrayList<>();

        users.add(new User("Gyula",30, true));
        users.add(new User("Maki", 29, false));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return users;
    }

    @RequestMapping(value = "/admin/{admin}", method = RequestMethod.GET)
    public List<User> getAdmins(@PathVariable boolean admin){
        return  users.stream().filter(x -> x.isAdmin() == admin)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<User> create(@RequestBody User user){
        users.add(user);

        return users;
    }
}
