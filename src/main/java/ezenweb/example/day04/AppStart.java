package ezenweb.example.day04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run( AppStart.class);
    }
}

// 프레젠테이션 레이어
    // Controller,View   -> DTO
// 비즈니스 레이어
    // Service          -> DTO , Entity
// 퍼시스턴스 레이어
    // Repository       -> Entity
// 데이터페이스 레이어
    // Mysql


// 스프링 MVC
    // Modal -> 비스니스 영역
    // View , Controller -> 프레젠테이션 레이어 영역
    // 데이터접근계층 -> DAO , Repository