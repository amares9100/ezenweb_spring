package ezenweb.example.day02.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


// 스프링에서 관리하는 IOC컨테이너(빈객체) 등록
@Controller // HTTP요청이 왔을때 해당 클래스로 핸들러 매핑
@Slf4j  // 스프링 로그 메소드 제공 [레벨 : trace(디버그보다 세분화된 정보확인), debug(디버깅 정보 확인)
        // info(진행상ㅎ뢍 일반정보 로그), warn(경고성 오류 정보 로그), error(오류발생한 오류정보 로그)]
public class MappingController {

    @RequestMapping(value = "/orange", method = RequestMethod.GET)
    @ResponseBody // 응답
    public  String  GetOrange(){
        System.out.println("클라이언트로부터 GetOrange메소드 요청이 들어옴");
        log.info("클라이언트로부터 GetOrange메소드 요청이 들어옴");
        return "<input type=text name=orange value=GET>";
    }

    @RequestMapping(value = "/orange", method = RequestMethod.POST)
    @ResponseBody // 응답
    public  String  PostOrange(){
        System.out.println("클라이언트로부터 PostOrange메소드 요청이 들어옴");
        log.info("클라이언트로부터 PostOrange메소드 요청이 들어옴");
        return "<input type=text name=orange value=POST>";
    }

    @RequestMapping(value = "/orange", method = RequestMethod.PUT)
    @ResponseBody // 응답
    public  String  PutOrange(){
        System.out.println("클라이언트로부터 PutOrange메소드 요청이 들어옴");
        log.info("클라이언트로부터 PutOrange메소드 요청이 들어옴");
        return "<input type=text name=orange value=PUT>";
    }

    @RequestMapping(value = "/orange", method = RequestMethod.DELETE)
    @ResponseBody // 응답
    public  String  DELETEOrange(){
        System.out.println("클라이언트로부터 DELETEOrange메소드 요청이 들어옴");
        log.info("클라이언트로부터 DELETEOrange메소드 요청이 들어옴");
        return "<input type=text name=orange value=DELETE>";
    }
}


// 크롬 or ajax or js -------요청-------> 서블릿컨테이너 ----------->DispatcherServlet
                                                                // 핸들러 매핑으로 해당 컨트롤러 검색
//                                                               Mapping 검색





















