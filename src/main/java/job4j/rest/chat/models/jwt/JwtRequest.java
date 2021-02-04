package job4j.rest.chat.models.jwt;

import lombok.*;

import java.io.Serializable;

/* Lombok */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class JwtRequest implements Serializable {
    
    private static final long serialVersionUID = 5926468583005150707L;
    
    private String username;
    private String password;
    
}