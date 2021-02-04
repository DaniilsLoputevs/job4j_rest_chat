package job4j.rest.chat.models;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/* Lombok */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
/* Spring JPA */
@Embeddable
public class Message {
    private int id;
    @ManyToOne
    private Person creator;
    private String content;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    
    public String prettyFormat() {
        return sdf.format(timestamp) + "   " + creator.getName() + ": " + content;
    }
    
}
