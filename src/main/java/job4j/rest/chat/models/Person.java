package job4j.rest.chat.models;

import lombok.*;

import javax.persistence.*;

/* Lombok */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
/* Spring JPA */
@Entity
@Table(name = "persons")
public class Person implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    
    // maybe need: userName & password
    
    
    /**
     * @implSpec Deep cloning.
     */
    @Override
    public Person clone() {
        return new Person(this.id, this.name, this.password);
    }
}
