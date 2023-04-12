package ezenweb.example.day02.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/getHtml")
public class ResourceMappingController {

    @GetMapping("/test1")
    public Resource getting(){
        return new ClassPathResource("/templates/test1.html");
    }


}
