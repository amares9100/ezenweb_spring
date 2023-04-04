package ezenweb.example.day02;

//클래스명 첫글자는 꼭 대문자
// 카멜표기법


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 해당 클래스보다 상위패키지에 존재하는 클래스는 스프링부트가 못읽어옴.
public class SpringStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringStart.class);
        System.out.println("시작");



    }
}
// @ComponentScan : 스프링 부트 실행될때 컴포넌트들을 스캔
    // @Controller : MVC패턴에서 컨트롤러
    // @Service : MVC패턴에서 서비스
    // @Repository : MVC패턴에서 데이터베이스
    // @Configuration : MVC패턴에서 설정


    // @EnableAutoConfiguration : 스프링 부트에 적용되는 설정
    // @ComponentScan : 스프링 부트에 적용되는 컴포넌트들

// bean : 스프링 IOC컨테이너가 관리하는 자바객체들















