package job4j.rest.chat.repositories;


import job4j.rest.chat.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {
    private final ConcurrentHashMap<String, Person> users = new ConcurrentHashMap<>();
    
    public void save(Person person) {
        users.put(person.getName(), person);
    }
    
    
    public Person findByUsername(String username) {
        return users.get(username);
    }
    
    public List<Person> findAll() {
        return new ArrayList<>(users.values());
    }
    
}