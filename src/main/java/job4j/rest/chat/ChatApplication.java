package job4j.rest.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {
    
    /* Spring security bean */
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
        System.err.println("APP RUN");
    }
    
}
