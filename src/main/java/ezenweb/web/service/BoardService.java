package ezenweb.web.service;

import ezenweb.web.domain.board.*;
import ezenweb.web.domain.member.MemberDto;
import ezenweb.web.domain.member.MemberEntity;
import ezenweb.web.domain.member.MemberEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import javax.transaction.Transactional;
import java.util.*;

@Service @Slf4j
public class BoardService {

    @Autowired private CategoryRepository categoryRepository;
    @Autowired private BoardEntityRepository boardEntityRepository;
    @Autowired private MemberEntityRepository memberEntityRepository;
    @Autowired private ReplyEntityRepository replyEntityRepository;
    @Autowired private RereplyEntityRepository rereplyEntityRepository;

    // 1. 카테고리 등록
    @Transactional
    public boolean categoryWrite( BoardDto boardDto ){ log.info("s board dto : " + boardDto );
        CategoryEntity entity = categoryRepository.save( boardDto.toCategoryEntity() ); // 1. 입력받은 cname 을 Dto 에서 카테고리 entity 형변환 해서 save
        if( entity.getCno() >= 1 ){ return true; }  // 2. 만약에 생성된 엔티티의 pk가 1보다 크면 save 성공
        return false;
    }
    // 2. 모든 카테고리 출력
    @Transactional
    public List<CategoryDto> categoryList(  ){    log.info("s categoryList : " );
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        // 형변환 List<엔티티> ---> MAP
        List<CategoryDto> list = new ArrayList();
        categoryEntityList.forEach( (e)->{
            list.add( new CategoryDto(e.getCno() , e.getCname()));
        }); return list;
    }

    // 3. 게시물 쓰기
    @Transactional
    public byte write( BoardDto boardDto ){ log.info("s board dto : " + boardDto );
        // 1. 카테고리 엔티티 찾기 [ 왜?? 게시물엔티티 안에 카테고리 엔티티객체 대입 할려고 ]
        Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById( boardDto.getCno() ); // 1. 선택된 카테고리 번호를 이용한 카테고리 엔티티 찾기
        if( !categoryEntityOptional.isPresent() ){ return 1; }         // 2. 만액에 선택된 카테고리가 존재하지 않으면  리턴
        CategoryEntity categoryEntity = categoryEntityOptional.get();    // 3. 카테고리 엔티티 추출
        // 2.로그인된 회원의 엔티티 찾기 [ JSP : request.getSession().getAttribute()  ]
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 1. 인증된 인증 정보  찾기
        if( o.equals("anonymousUser") ){ return 2; }
        MemberDto loginDto = (MemberDto)o;   // 2. 형변환
        MemberEntity memberEntity = memberEntityRepository.findByMemail( loginDto.getMemail() ); // 3. 회원엔티티 찾기
        // 3. 게시물 쓰기
        BoardEntity boardEntity = boardEntityRepository.save( boardDto.toBoardEntity() );
        if( boardEntity.getBno() < 1 ){ return  3; }
        // 4. 양방향 관계 [ 카테고리안에 게시물 힙[주소] 대입 , 게시물안에 카테고리 힙[주소] 대입 ]
        categoryEntity.getBoardEntityList().add( boardEntity ); // 1. 카테고리 엔티티에 생성된 게시물 등록
        boardEntity.setCategoryEntity( categoryEntity ); // 2. 생성된 게시물에 카테고리 엔티티 등록
        // 5. 양방향 관계
        boardEntity.setMemberEntity( memberEntity ); // 1. 생성된 게시물 엔티티에 로그인된 회원 등록
        memberEntity.getBoardEntityList().add(boardEntity);  // 2. 로그인된 회원 엔티티에 생성된 게시물 엔티티 등록

        // 공지사항 게시물 정보 확인
        /*
            Optional<CategoryEntity> optionalCategory =  categoryRepository.findById( 1 );
            log.info( "공지사항 카테고리 엔티티 확인 :" + optionalCategory.get() );
            log.info( "공지사항 카테고리 엔티티 확인 :" + optionalCategory.get().getBoardEntityList().get(1).getMemberEntity().getMemail() );
        */
        return 4;
    }
    // 4. 카테고리별 게시물 출력
    @Transactional
    public PageDto list(  PageDto pageDto){ log.info("s list cno : " + pageDto );
        List<BoardDto> list = new ArrayList<>();
        // PageRequest.of(페이지번호[0시작] , 페이지당 표시수 , Sort,by(Sort.Direction.DESC/ASC , "정렬기준필드명"));
        Pageable pageable = PageRequest.of(pageDto.getPage()-1 , 3 , Sort.by(Sort.Direction.DESC , "bno"));
        Page<BoardEntity> entityPage = boardEntityRepository.findBySearch(pageDto.getCno() ,pageDto.getKey() , pageDto.getKeyword() ,  pageable);


        entityPage.forEach((b)->{
            list.add(b.toDto());
        });
        pageDto.setBoardDtoList(list);
        pageDto.setTotalPage(entityPage.getTotalPages());
        pageDto.setTotalCount(entityPage.getTotalElements());
        return pageDto;
    }

    // 5. 내가 쓴 게시물 출력
    public List<BoardDto> myboards( ){log.info("s myboards : " );
        // 1. 로그인 인증 세션[object] --> dto 강제형변환
        MemberDto memberDto = (MemberDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 일반회원dto : 모든 정보 , oauth2dto : memail , mname , mrol
        // 2. 회원 엔티티 찾기
        MemberEntity entity =  memberEntityRepository.findByMemail( memberDto.getMemail() );
        // 3. 회원 엔티티 내 게시물리스트를 반복문 돌려서 dto 리스트 로 변환
        List<BoardDto> list = new ArrayList<>();
        entity.getBoardEntityList().forEach( (e)->{
            list.add( e.toDto() );
        });
        return list;
    }

    // 선택한 글 출력 + 선택한 글의 댓글포함
    @Transactional
    public BoardDto selectList(int bno){
        // 선택한 bno 검색
        BoardEntity entity = boardEntityRepository.findByBno(bno);

        List<ReplyDto> list= new ArrayList<>();
        // 선택한 bno 의 댓글리스트의 반복문
        entity.getReplyEntityList().forEach( (r)->{

            List<RereplyDto> list2= new ArrayList<>();
            // 각 댓글의 반복문으로 대댓글 불러오기
            r.getRereplyEntitiyList().forEach( (rr) -> {
                // 불러온 대댓글을 list2에 담기
                list2.add( rr.toDto() );
            } );
            // 댓글의 DTO를 만들어서 그곳에 대댓글리스트 담기
            ReplyDto replyDto = r.toDto();
            replyDto.setRereplyDtoList( list2 );
            // 댓글과 대댓글이 담긴 DTO를 list에 담기
            list.add( replyDto );
        });
        // 보드 DTO를 만들어서 담은 댓글과 대댓글을 보드에 담기
        BoardDto dto = entity.toDto();
        dto.setReplyEntityList(list);
        return dto;
}

    // 글삭제
    @Transactional
    public boolean onDelete(int bno){

        Optional<BoardEntity> entity = boardEntityRepository.findById(bno);
    if(entity.isPresent()){
        boardEntityRepository.delete(entity.get());
        return true;
    }


        return false;
    }

    // 글수정
    @Transactional
    public boolean listUpdate(BoardDto dto){
        Optional<BoardEntity> entityOptional = boardEntityRepository.findById(dto.getBno());

        if(entityOptional.isPresent()){

            BoardEntity entity = entityOptional.get();

            entity.setBcontent(dto.getBcontent());
            entity.setBtitle(dto.getBtitle());
            entity.setCategoryEntity(dto.toCategoryEntity());


            return true;
        }


    return false;
    }


    // reply-----------------------------------------------------

    // 댓글쓰기
    @Transactional
    public boolean replyWrite(ReplyDto dto){

        Optional<BoardEntity> entityOptional = boardEntityRepository.findById(dto.getBno());
        BoardEntity boardEntity = entityOptional.get();

        Optional<MemberEntity> memberEntityOptional = memberEntityRepository.findById(dto.getMno());
        MemberEntity memberEntity = memberEntityOptional.get();


        ReplyEntity entity =  replyEntityRepository.save(dto.toEntity());

        // 양방향 설정
        entity.setMemberEntity(memberEntity);
        entity.setBoardEntity(boardEntity);
        memberEntity.getReplyEntityList().add(entity);
        boardEntity.getReplyEntityList().add(entity);

    if(entity == null){
        return false;
    }
    return true;
    }

    // 댓글삭제
    @Transactional
    public boolean replyDelete(int rno){

        ReplyEntity replyEntity = replyEntityRepository.findById(rno).get();
        if(replyEntity == null){
            return false;
        }
        replyEntityRepository.delete(replyEntity);

        return true;
    }

    // 댓글수정
    @Transactional
    public boolean replyUpdate(ReplyDto dto){

        Optional<ReplyEntity> replyEntity = replyEntityRepository.findById(dto.getRno());
        if(replyEntity == null){
            return  false;
        }

        ReplyEntity reply = replyEntity.get();

        reply.setRcontent(dto.getRcontent());
        replyEntityRepository.save(reply);

        return true;
    }

    @Transactional
    public boolean rereplyWrite(RereplyDto dto){

        Optional<ReplyEntity> entityOptional = replyEntityRepository.findById(dto.getRno());
        ReplyEntity replyEntity = entityOptional.get();

        Optional<MemberEntity> memberEntityOptional = memberEntityRepository.findById(dto.getMno());
        MemberEntity memberEntity = memberEntityOptional.get();



        RereplyEntity entity = rereplyEntityRepository.save(dto.toEntity());


        // 양방향 설정
        entity.setMemberEntity(memberEntity);
        entity.setReplyEntity(replyEntity);
        memberEntity.getRereplyEntitiyList().add(entity);
        replyEntity.getRereplyEntitiyList().add(entity);

        if(entity == null){
            return false;
        }
        return true;
        }
    @Transactional
    public boolean rereplyUpdate(RereplyDto dto){
        log.info("rereplyUpdate : " + dto);

        Optional<RereplyEntity> entity = rereplyEntityRepository.findById(dto.getRrno());
        if(entity == null){
            return  false;
        }

        RereplyEntity rereplyEntity = entity.get();

        rereplyEntity.setRrcontent(dto.getRrcontent());
        rereplyEntityRepository.save(rereplyEntity);

        return true;
    }

    @Transactional
    public boolean rereplyDelete(int rrno){

        Optional<RereplyEntity> optionalRereply = rereplyEntityRepository.findById(rrno);
        if(optionalRereply == null){
            return  false;
        }

        RereplyEntity entity = optionalRereply.get();

        rereplyEntityRepository.delete(entity);

        return true;
    }
}