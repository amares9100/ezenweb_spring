package ezenweb.web.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardEntityRepository extends JpaRepository< BoardEntity , Integer > {
   // 직접 sql 작성
    // JPS = select * from board where cno = ?
    //          ps.setInt(1 , cno);

    // JPA = select * from board where cno= :cno
    // cno가 0이면( 전체보기 ) 일땐 적용안됨
    @Query(value = "select * from board where if( :cno = 0 , cno like '%%' , cno =:cno)" , nativeQuery = true)
    Page<BoardEntity> findBySearch(int cno, Pageable pageable);
}
