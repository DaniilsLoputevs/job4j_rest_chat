package job4j.rest.chat.controllers;

import job4j.rest.chat.aop.Log;
import job4j.rest.chat.models.Person;
import job4j.rest.chat.repositories.PersonRepository;
import job4j.rest.chat.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * findByID
 * findAll
 * create
 * update
 * delete
 */
@RestController
@RequestMapping("api/person")
public class PersonController {
    @Autowired
    RoomService roomService;
    @Autowired
    PersonRepository roomRepository;
    
    
    @GetMapping("/")
    @Log
    public List<Person> findAll() {
        return StreamSupport.stream(
                this.roomRepository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @Log
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.roomRepository.findById(id);
        return new ResponseEntity<Person>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
    
    @PostMapping("/")
    @Log
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return new ResponseEntity<>(
                this.roomRepository.save(person),
                HttpStatus.CREATED
        );
    }
    
    @PutMapping("/")
    @Log
    public ResponseEntity<Void> update(@RequestBody Person person) {
        this.roomRepository.save(person);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    @Log
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.roomRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
