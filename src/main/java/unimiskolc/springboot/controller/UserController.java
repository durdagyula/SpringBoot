package unimiskolc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unimiskolc.springboot.model.User;
import unimiskolc.springboot.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository userRepository;

    private User currentUser = null;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
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

    @RequestMapping(value = "/authenticate/{name}/{psw}", method = RequestMethod.POST)
    public boolean authUser(@PathVariable String name,@PathVariable String psw){
        List<User> users = userRepository.findAll();
        User _user = new User();
        boolean response = false;

        for(int i = 0; i < users.size(); i++){
            _user = users.get(i);
            if((_user.getUserName().equals(name))&&(_user.getUserPassword().equals(psw))){
                setUser(_user);
                response = true;
                break;
            }
        }

        return response;
    }

    @RequestMapping(value = "/add/{name}/{credits}/{password}/{isAdmin}", method = RequestMethod.POST)
    public boolean addUser(@PathVariable String name,@PathVariable int credits, @PathVariable String password, @PathVariable boolean isAdmin){
        List<User> users = userRepository.findAll();
        User _user = new User();
        boolean response = true;

        for(int i = 0; i < users.size(); i++){
            _user = users.get(i);
            if(_user.getUserName().equals(name)){
                setUser(_user);
                response = false;
                break;
            }
        }

        if(response) {
            users.add(new User(name,credits,isAdmin,password));
            userRepository.save(users);
        }

        return response;
    }

    @RequestMapping(value = "/register/{name}/{credits}/{password}", method = RequestMethod.POST)
    public boolean registerUser(@PathVariable String name,@PathVariable int credits, @PathVariable String password){
        List<User> users = userRepository.findAll();
        User _user = new User();
        boolean response = true;

        for(int i = 0; i < users.size(); i++){
            _user = users.get(i);
            if(_user.getUserName().equals(name)){
                setUser(_user);
                response = false;
                break;
            }
        }

        if(response) {
            users.add(new User(name,credits,false,password));
            userRepository.save(users);
        }

        return response;
    }

    private void setUser(User user){
        currentUser = user;
    }
    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public User getCurrentUser(){
        return currentUser;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public User logout(){
        currentUser = null;
        return currentUser;
    }
}
