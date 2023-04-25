package ezenweb.web.controller;

import ezenweb.web.domain.member.MemberDto;
import ezenweb.web.domain.todo.PageDto;
import ezenweb.web.domain.todo.TodoDto;
import ezenweb.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo")
//@CrossOrigin(origins = "http://localhost:3000") // 해당 컨트롤러는 http://localhost:3000 요청 CORS 정책 적용
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("")
    public PageDto get(@RequestParam int page ){    // TodoDto , 서비스 , 리포지토리 , 엔티티  작업

        PageDto list = todoService.getTodo(page);
        return list;
    }
    @PostMapping("")
    public boolean post( @RequestBody TodoDto todoDto ){

        boolean result = todoService.create(todoDto);
        return result;
    }
    @PutMapping("")
    public boolean put(@RequestBody TodoDto todoDto ){
        boolean result = todoService.update(todoDto);
        return result;
    }
    @DeleteMapping("")
    public boolean delete( @RequestParam int id ){
        boolean result = todoService.delete(id);
        return result;
    }
}

