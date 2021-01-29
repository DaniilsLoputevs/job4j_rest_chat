package job4j.rest.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/* Lombok */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
/* Spring JPA */
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @OneToMany
    public Set<Person> members = new HashSet<>();
    
    @ElementCollection
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "msg_id")),
            @AttributeOverride(name = "creator", column = @Column(name = "msg_creator")),
            @AttributeOverride(name = "content", column = @Column(name = "msg_content"))
    })
    private List<Message> messages = new LinkedList<>();
    
    public void addMsg(Message msg) {
        messages.add(msg);
    }
    
    public void addPerson(Person person) {
        System.out.println("add person: " + person);
        System.out.println("members before:" + members.size());
        members.add(person);
        System.out.println("members before:" + members.size());
    }
    
    public boolean contains(Person expectedMember) {
        return members.contains(expectedMember);
    }
    
    public boolean contains(Message expectedMsg) {
        return messages.contains(expectedMsg);
    }
    
    /**
     * {@code @JsonIgnore } -- (why it is here?) - this method isn't a getter
     * for field or object property, but Spring mapping it into JSON for requests.
     */
    @JsonIgnore
    public Message getLsatMsg() {
        return (messages.isEmpty()) ? new Message() : messages.get(messages.size() - 1);
    }
    
}
