package job4j.rest.chat.controllers;

import job4j.rest.chat.models.Person;
import job4j.rest.chat.repositories.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserStore userStore;
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    public UserController(UserStore users, BCryptPasswordEncoder encoder) {
        this.userStore = users;
        this.encoder = encoder;
    }
    
    /**
     * Generate JWT - URL mapping.
     */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        userStore.save(person);
    }
    
    @GetMapping("/all")
    public List<Person> findAll() {
        return userStore.findAll();
    }
    
}