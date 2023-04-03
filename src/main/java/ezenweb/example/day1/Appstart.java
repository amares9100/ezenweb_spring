package ezenweb.example.day1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // 스프링부트
public class Appstart {
    @Autowired
    private static Testmember2repository testmember2repository;

    public static void main(String[] args) {
        SpringApplication.run(Appstart.class);
        lomDto dto = lomDto.builder().mid("asd").mpassword("Asd").build();


        testmember2repository.save(dto.toEntity());
    }



}
/*
* Description:

    !! resources -> application.properties 설정값이 누락 혹은 오타
    * application.properties 파일 생성후 기본 설정값 입력 (띄어쓰기는 하지말것)
    Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

    Reason: Failed to determine a suitable driver class




* */