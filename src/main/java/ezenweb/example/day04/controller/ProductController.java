package ezenweb.example.day04.controller;


import ezenweb.example.day04.domain.dto.*;
import ezenweb.example.day04.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // 컨트롤러
@RequestMapping("/item")
public class ProductController {
    // 컨테이너에 등록된 서비스 빈 자동 주입
    @Autowired
    private ProductService service;

    // 1.  [REACT.JS 사용하기 전]   HTML 반환
    @GetMapping("")
    public Resource index(){
        return new ClassPathResource("templates/item.html");
    }
    // 2. Restful API
    // 1. 저장
    @PostMapping("/write")
    public boolean write(@RequestBody ProductDto dto ){
        return service.write(dto);
    }
    // 2. 수정
    @PutMapping("/update")
    public boolean update(@RequestBody ProductDto dto ){
        return service.update(dto);
    }

    // 3. 삭제
    @DeleteMapping("/delete")
    public boolean delete( @RequestParam int pno ){

        return service.delete(pno);
    }

    @GetMapping("/get")
    public ArrayList<ProductDto> get(){
        ArrayList<ProductDto> list = service.print();
        return list;
    }

    // 3. 유효성검사


}