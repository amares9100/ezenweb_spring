package ezenweb.example.day1Q;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 해당 클래스는 스프링 컨테이터 등록
public class BoardController {

    @Autowired // 빈에 등록된 생성자를 찾아서 자동등록
    private BoardRepository boardRepository;

    @GetMapping("/") // @GetMapping("url")      HTTP GET 메소드 매핑(연결)
    public String index(){
        BoardEntity entity = BoardEntity.builder()
                .btitle("제목")
                .bcontent("내용")
                .build();
        boardRepository.save(entity);
        return "메인페이지";
    }

    }

