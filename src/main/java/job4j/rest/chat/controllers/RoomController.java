package job4j.rest.chat.controllers;

import job4j.rest.chat.aop.Log;
import job4j.rest.chat.models.Message;
import job4j.rest.chat.models.Room;
import job4j.rest.chat.repositories.RoomRepository;
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
 * +++++++
 * put smg into chat(by id)
 * put person into chat(by id)
 */
@RestController
@RequestMapping("api/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomRepository roomRepository;
    
    
    @GetMapping("/")
    @Log
    public List<Room> findAll() {
        return StreamSupport.stream(
                this.roomRepository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    @Log
    public ResponseEntity<Room> findById(@PathVariable int id) {
        var person = this.roomRepository.findById(id);
        return new ResponseEntity<Room>(
                person.orElse(new Room()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
    
    @PostMapping("/")
    @Log
    public ResponseEntity<Room> create(@RequestBody Room room) {
        return new ResponseEntity<>(
                this.roomRepository.save(room),
                HttpStatus.CREATED
        );
    }
    
    @PutMapping("/")
    @Log
    public ResponseEntity<Void> update(@RequestBody Room room) {
        this.roomRepository.save(room);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    @Log
    public ResponseEntity<Void> delete(@PathVariable int id) {
        this.roomRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    
    /**
     * PUT http://localhost:8080/room/person?roomID=*value*&personID=*value*
     */
    @PostMapping("/person/")
    @Log
    public ResponseEntity<Void> postPerson(@RequestParam("roomID") int roomId,
                                          @RequestParam("personID") int personId) {
        roomService.acceptPerson(roomId, personId);
        return ResponseEntity.ok().build();
    }
    
    /**
     * PUT http://localhost:8080/room/msg?roomID=*value*
     */
    @PostMapping("/msg/")
    @Log
    public ResponseEntity<Void> postMsg(@RequestParam("roomID") int roomId,
                                        @RequestBody Message msg) {
        roomService.acceptMsg(roomId, msg);
        roomService.outLastMsg(roomId, System.out::println);
        return ResponseEntity.ok().build();
    }
    
}
