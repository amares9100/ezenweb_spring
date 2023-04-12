package ezenweb.web.service;

import ezenweb.web.domain.member.MemberDto;
import ezenweb.web.domain.member.MemberEntity;
import ezenweb.web.domain.member.MemberEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@Service // 서비스 레이어
@Slf4j
public class MemberService {

    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired private HttpServletRequest request; // 요청 객체

    // 1. 회원가입
    @Transactional
    public boolean write( MemberDto memberDto ){
        // 스프링 시큐리티에서 제공하는 암호화[ 사람이 이해하기 어렵고 컴퓨터는 이해하기 쉬운단어 ] 사용하기
        // 인코더 : 형식변경 // 디코더 : 원본으로 되돌리기
            // DB내에서 정보 감추기


        // 입력받은 DTO의 패스워드를 암호화해서 다시 DTO에 저장
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setMpassword(passwordEncoder.encode( memberDto.getMpassword() ) );



        MemberEntity entity = memberEntityRepository.save( memberDto.toEntity() );
        if( entity.getMno() > 0 ){ return  true;}
        return false;
    }
    // *2 로그인 [ 시큐리티 사용 하기 전 ]
    @Transactional
    public boolean login( MemberDto memberDto  ){

        // 1. 입력받은 이메일 로 엔티티 찾기
        MemberEntity entity = memberEntityRepository.findBymemail( memberDto.getMemail() );
            log.info( "entity : " + entity );
         // 2. 패스워드 검증
            // 엔티티 안에있는 입력받은 패스워드와 (암호화된)패스워드 비교
        if( new BCryptPasswordEncoder().matches( memberDto.getMpassword(), entity.getMpassword() ) ){
            // == 스택 메모리 내 데이터 비교
            // .equals() 힙 메모리 내 데이터 비교
            // matches() : 문자열 주어진 패턴 포함 동일여부 체크
            // 세션 사용 : 메소드 밖 필드에 @Autowired private HttpServletRequest request;
            request.getSession().setAttribute("login", entity.getMemail() );
            return true;
        }

        /*
        // 2. 입력받은 이메일과 패스워드가 동일한지
        Optional<MemberEntity> result = memberEntityRepository.findByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
        log.info("result : " + result  );
        if( result.isPresent()){
            request.getSession().setAttribute("login", result.get().getMno() );
            return  true;
        }
        */
        /*
        // 3.
        boolean result = memberEntityRepository.existsByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
        log.info( "result : " + result );
        if( result == true ){ request.getSession().setAttribute("login", memberDto.getMemail()); return true; }
         */
        return false;
    }
    // 2. [세션에 존재하는 ] 회원정보
    @Transactional
    public MemberDto info(  ){
        String memail = (String)request.getSession().getAttribute("login");
        if( memail != null ){
            MemberEntity entity = memberEntityRepository.findByMemail( memail );
            return entity.todto();
        }
        return null;
    }
    // 2. [ 세션에 존재하는 정보 제거 ] 로그아웃
    @Transactional
    public boolean logout(){ request.getSession().setAttribute("login",null); return true; }

    // 3. 회원수정
    @Transactional
    public boolean update(  MemberDto memberDto ){
        Optional<MemberEntity> entityOptional = memberEntityRepository.findById( memberDto.getMno() );
        if( entityOptional.isPresent() ){
            MemberEntity entity = entityOptional.get();
            entity.setMname( memberDto.getMname());  entity.setMphone( memberDto.getMphone() );
            entity.setMrole( memberDto.getMrole() ); entity.setMpassword( memberDto.getMpassword());
            return true;
        }
        return false;
    }
    // 4. 회원탈퇴
    @Transactional
    public boolean delete( int mno ){
        Optional<MemberEntity> entityOptional = memberEntityRepository.findById( mno );
        if( entityOptional.isPresent()){
            MemberEntity entity = entityOptional.get();
            memberEntityRepository.delete( entity );
            return true;
        }
        return false;
    }
}