package lv.progmeistars.webquiz.greeting;

import lv.progmeistars.webquiz.greeting.obj.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // usually used with JSON
@RequestMapping("/rest")
public class GreetingRestController {

    @GetMapping("greeting")
    public String greeting() {
        return "Hello World";
    }

    @GetMapping("user")
    public ResponseEntity<User> helloUser() { // returns JSON

        User user = new User();
        user.setUsername("John");
        user.setKarma(100);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
