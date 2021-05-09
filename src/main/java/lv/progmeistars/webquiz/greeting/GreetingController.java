package lv.progmeistars.webquiz.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // working with MVC, templates
@RequestMapping(path = "/greeting")
public class GreetingController {


    // http://localhost:8081/greeting?username=Aleksandr

    @GetMapping
    public String greeting(Model model, @RequestParam(name = "username", required = false, defaultValue = "World") String username) {

        model.addAttribute("name", username);

        return "hello";
    }

//    @GetMapping("/hello-world")
//    public String greeting() {
//
//    }
//
//    @GetMapping("/hello-user")
//    public String greeting() {
//
//    }
}
