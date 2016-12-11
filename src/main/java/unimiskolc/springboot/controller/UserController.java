package unimiskolc.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import sun.misc.JavaUtilZipFileAccess;
import unimiskolc.springboot.SmtpMailSender;
import unimiskolc.springboot.model.User;
import unimiskolc.springboot.repository.UserRepository;
import unimiskolc.springboot.service.EmailService;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
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

    @RequestMapping(value = "/edit/{name}/{credits}/{isAdmin}/{selectedUser}", method = RequestMethod.POST)
    public boolean addUser(@PathVariable String name,@PathVariable int credits, @PathVariable boolean isAdmin, @PathVariable String selectedUser){
        List<User> users = userRepository.findAll();
        boolean response = true;
        int count = 0;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equals(name) ){
                count++;
            }
            if(users.get(i).getUserName().equals(selectedUser) ){
                count++;
            }
        }

        if(count>1){
            return false;
        }

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUserName().equals(selectedUser) ){
                users.get(i).setUserName(name);
                users.get(i).setUserCredit(credits);
                users.get(i).setAdmin(isAdmin);
                response = true;
                break;
            }
            response = false;
        }

        if(response) {
            userRepository.save(users);
        }

        return response;
    }

    @RequestMapping(value = "/register/{name}/{credits}/{email}/{password}", method = RequestMethod.POST)
    public boolean registerUser(@PathVariable String name,@PathVariable int credits, @PathVariable String password, @PathVariable String email) throws MessagingException{
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
            User newUser = new User(name,credits,false,password);
            users.add(newUser);
            userRepository.save(users);
            try {
                this.emailService.sendEmail(newUser,email);
            }catch (MailException e){

            }
        }

        return response;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public User logout(){
        currentUser = null;
        return currentUser;
    }

    private void setUser(User user){
        currentUser = user;
    }
    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public User getCurrentUser(){
        return currentUser;
    }
}
