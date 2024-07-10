package toyProject.toyProject01.old;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class test {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
