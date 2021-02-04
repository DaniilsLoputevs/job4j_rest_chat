package job4j.rest.chat.repositories;

import job4j.rest.chat.models.Person;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
//@Component
public class UserStore {
    private final ConcurrentHashMap<String, Person> users = new ConcurrentHashMap<>();

    public void save(Person person) {
        users.put(person.getName(), person);
    }

    @PostConstruct
    private void init() {
        users.put("Kirin", new Person(0, "Kirin", "password"));
        users.put("Kayto", new Person(1, "admin", "admin"));
    }


    public Person findByUsername(String username) {
        return users.get(username);
    }

    public List<Person> findAll() {
        return new ArrayList<>(users.values());
    }

}