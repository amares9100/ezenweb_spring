package ezenweb.example.day1Q;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // MVC , 내장톰캣 , restful , 스프링컨테이너 , 기본세팅등등 적용
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }

}
