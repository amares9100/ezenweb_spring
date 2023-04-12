package ezenweb.web.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("") // local host 8080
    public Resource getIndex() {
        return new ClassPathResource("templates/index.html");
    }
}
