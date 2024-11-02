package controle.jee.controleGCC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/accueil")
    public String index() {
        return "accueil";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
