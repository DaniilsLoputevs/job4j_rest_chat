package job4j.rest.chat.a_example_jwt.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = {"*"})
public class HelloWorldController {
    
    @GetMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }
    
}