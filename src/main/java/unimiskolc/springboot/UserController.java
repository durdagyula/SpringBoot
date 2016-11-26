package unimiskolc.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public List<User> getAdmins(){
        return  userRepository.findByisAdminTrue();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<User> create(@RequestBody User user){
        userRepository.save(user);

        return userRepository.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public List<User> remove(@PathVariable long id){
        userRepository.delete(id);

        return  userRepository.findAll();
    }
}
