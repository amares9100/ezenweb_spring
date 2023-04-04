package ezenweb.example.day02.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


// 스프링에서 관리하는 IOC컨테이너(빈객체) 등록
@RestController // HTTP요청이 왔을때 해당 클래스로 핸들러 매핑 / @ResponseBody + @Controller
@Slf4j  // 스프링 로그 메소드 제공 [레벨 : trace(디버그보다 세분화된 정보확인), debug(디버깅 정보 확인)
        // info(진행상ㅎ뢍 일반정보 로그), warn(경고성 오류 정보 로그), error(오류발생한 오류정보 로그)]
@RequestMapping("/Color")
public class MappingController4 {

    @GetMapping(value = "/Pink")

    public  String  GetPink(){
        System.out.println("클라이언트로부터 GetPink 요청이 들어옴");
        log.info("클라이언트로부터 GetPink 요청이 들어옴");
        return "<input type=text name=orange value=GetPink>";
    }

    @PostMapping(value = "/Pink")

    public  String  PostPink(){
        System.out.println("클라이언트로부터 PostPink 요청이 들어옴");
        log.info("클라이언트로부터 PostPink 요청이 들어옴");
        return "<input type=text name=orange value=PostPink>";
    }

    @PutMapping(value = "/Pink")

    public  String  PutPink(){
        System.out.println("클라이언트로부터 PutPink 요청이 들어옴");
        log.info("클라이언트로부터 PutPink 요청이 들어옴");
        return "<input type=text name=orange value=PutPink>";
    }

    @DeleteMapping(value = "/Pink")

    public  String  DELETEPink(){
        System.out.println("클라이언트로부터 DELETEPink 요청이 들어옴");
        log.info("클라이언트로부터 DELETEPink 요청이 들어옴");
        return "<input type=text name=orange value=DELETEPink>";
    }
}


// 크롬 or ajax or js -------요청-------> 서블릿컨테이너 ----------->DispatcherServlet
                                                                // 핸들러 매핑으로 해당 컨트롤러 검색
//                                                               Mapping 검색





















