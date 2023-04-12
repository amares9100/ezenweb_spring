package ezenweb.web.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository< MemberEntity , Integer > {
    // 1. 해당 이메일 로 엔티티 찾기
    // 인수로 들어온 email과 동일한 엔티티[레코드] 찾아서 반환
    // sql :  select * from member where memail = ? ;
    MemberEntity findByMemail(String memail);
    // 2. 해당 이메일과 비밀번호가 일치한 엔티티 여부 확인
    // 인수로 들어온 이메일 과 패스워드가 모두 일치한 엔티티[레코드] 찾아서 존재 여부 반환
    // sql : select * from member where memail = ? and mpassword = ? ;
    //Optional<MemberEntity> findByMemailAndMPassword(String memail, String mpassword);
    //Optional<MemberEntity> findBymemailAndMpassword( String memail , String mpassword);
    // 아이디 중복체크
    //boolean exitsByMemail(String memail);
    //boolean exitsByMpasswordAndMemail(String mpassword , String memail);

    // 아이디 찾기 [ 이름과 전화번호가 일치하면 ]

    // 비밀번호 찾기 [ 아이디와 전화번호 ]

    boolean existsByMemailAndMpassword(String memail, String mpassword);



    // 직접만들기 예시
    //@Query("select * from MemberEntity m where m.memail =?1")
   // MemberEntity findId(String memail);

}
/*
    .findById( pk값 ) : 해당하는 pk값이 검색후 존재하면 레코드[엔티티] Optional<반환>
    .findAll()  : 모든 레코드[엔티티] 반환
    .save( 엔티티 ) : 해당 엔티티를 DB레코드 에서 innert
    .delete( 엔티티 ) : 해당 엔티티를 DB레코드 에서 delete
    @Transactional --> setter 메소드 이용    : 수정
    ---------- 그외 추가 메소드  만들기
    검색 [ 레코드 반환 ] 필드명 : 첫글자 대문자 [ 카멜표기법 ]
        .findBy필드명( 인수 )       select * from member where memail = ? ;
        .findBy필드명And필드명       select * from member where memail = ? and mpassword = ? ;
        .findBy필드명or필드명
    *
        Optional 필수 X
            MemberEntity
            Optional<MemberEntity>

    검색된 레코드 반환
        Optional<MemberEntity>      : 레코드 1개
        List<MemberEntity>          : 레코드 여러개



 */