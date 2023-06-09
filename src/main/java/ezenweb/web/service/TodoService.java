package ezenweb.web.service;

import ezenweb.web.domain.board.BoardEntity;
import ezenweb.web.domain.member.MemberEntity;
import ezenweb.web.domain.todo.PageDto;
import ezenweb.web.domain.todo.TodoDto;
import ezenweb.web.domain.todo.TodoEntity;
import ezenweb.web.domain.todo.TodoEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 서비스 레이어
@Slf4j
public class TodoService {

    @Autowired
    TodoEntityRepository todoEntityRepository;

    //생성하기
    @Transactional
    public boolean create(TodoDto todoDto){

        TodoEntity todoEntity = todoEntityRepository.save( todoDto.todoEntity());

        if( todoEntity.getId() < 1 ){ return  false; }

        return true;
    }

    // 불러오기
    @Transactional
    public PageDto getTodo(int page){
        List<TodoDto> list = new ArrayList();

        Pageable pageable = PageRequest.of(page-1 , 5 , Sort.by(Sort.Direction.DESC , "id"));

        Page<TodoEntity> todoEntity = todoEntityRepository.findAll(pageable);
        log.info("List of todo" + todoEntity.toString());
        todoEntity.forEach( (e) ->{
            list.add(e.toDto());

        });


        return PageDto.builder()
                .todoDtoList(list)
                .totalCount(todoEntity.getTotalElements())
                .totalPage(todoEntity.getTotalPages())
                .page(page)
                .build();
    }

    @Transactional
    public boolean update(TodoDto todoDto){
        Optional<TodoEntity> todoEntityOptional = todoEntityRepository.findAllById(todoDto.getId());

        TodoEntity entity = todoEntityOptional.get();

        entity.setTitle(todoDto.getTitle());
        return true;
    }

    @Transactional
    public boolean delete(int id){
        System.out.println(id);

        Optional<TodoEntity> todoEntityOptional = todoEntityRepository.findAllById(id);

        TodoEntity entity = todoEntityOptional.get();

        todoEntityRepository.delete(entity);

        return true;
    }

}
