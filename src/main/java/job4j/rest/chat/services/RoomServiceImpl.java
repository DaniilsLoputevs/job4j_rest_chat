package job4j.rest.chat.services;

import job4j.rest.chat.exceptions.MismatchesMsgAndChatPerson;
import job4j.rest.chat.models.Message;
import job4j.rest.chat.models.Person;
import job4j.rest.chat.models.Room;
import job4j.rest.chat.repositories.PersonRepository;
import job4j.rest.chat.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    PersonRepository personRepository;
    
    @Override
    public void acceptMsg(int roomId, Message msg) {
        Room room = findRoom(roomId);
        System.err.println("members: " + room.getMembers());
        if (room.contains(msg.getCreator())) {
            room.addMsg(msg);
            roomRepository.save(room);
        } else throw new MismatchesMsgAndChatPerson();
    }
    
    @Override
    public void acceptPerson(int roomId, int personId) {
        Person newMember = personRepository.findById(personId).orElseThrow(IllegalStateException::new);
        System.out.println("newMember: " + newMember);
        Room room = findRoom(roomId);
        room.addPerson(newMember);
        roomRepository.save(room);
    }
    
    @Override
    public void outLastMsg(int roomId, Consumer<String> output) {
        Room room = findRoom(roomId);
        var sb = new StringBuilder();
        prettyFormatMsg(sb, room, room.getLsatMsg());
        output.accept(sb.toString());
    }
    
    @Override
    public void outAllMsg(int roomId, Consumer<String> output) {
        Room room = findRoom(roomId);
        var sb = new StringBuilder();
        room.getMessages().forEach(msg -> prettyFormatMsg(sb, room, msg));
        output.accept(sb.toString());
    }
    
    
    private Room findRoom(int roomId) {
        return roomRepository.findById(roomId).orElseThrow(IllegalStateException::new);
    }
    
    private void prettyFormatMsg(StringBuilder sb, Room room, Message msg) {
        sb.append('[').append(room.getName()).append(']').append("  ").append(msg.prettyFormat());
    }
    
}
