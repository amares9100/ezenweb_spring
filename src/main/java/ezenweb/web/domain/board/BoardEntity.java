package ezenweb.web.domain.board;

import ezenweb.web.domain.BaseTime;
import ezenweb.web.domain.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity@Table(name = "board")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;    // 게시물 번호

    @Column(nullable = false)
    private String btitle;  // 게시물제목 [기본값 255]

    @Column(columnDefinition = "longtext") // SQL 자료형 변경
    private String bcontent;

    @Column
    @ColumnDefault("0")
    private int bview; // 조회수

    // 작성일 수정일은 BaseTime에서 상속



    // Fk = 외래키
    // 카테고리번호
    @ManyToOne // 다수가 하나 에게 [ fk --> pk ]
    @JoinColumn(name="cno") // fk필드명
    @ToString.Exclude // 해당 필드는 @ToString 제외 필드 [ * 양방향 필수 ]
    private CategoryEntity categoryEntity;
    // 회원번호 [ 작성자 ]
    @ManyToOne
    @JoinColumn(name="mno")
    @ToString.Exclude
    private MemberEntity memberEntity;

    // Pk (양방향) 해당 댓글 엔티티의 Fk와 매핑
    // 댓글목록
    @OneToMany(mappedBy = "boardEntity")
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();


    // 출력용 Entity --> Dto
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno( this.bno ).btitle( this.btitle ) .bcontent( this.bcontent )
                .cno( this.getCategoryEntity().getCno() ).cname( this.getCategoryEntity().getCname() )
                .mno( this.getMemberEntity().getMno() ) .mname( this.getMemberEntity().getMname() )
                .bview( this.bview )
                .bdate(  // 날짜형변환  //  삼항 연산자  [    조건 ? 참 : 거짓 ]
                        //          만약에 작성 날짜/시간중 날짜가  현재 날짜와 동일하면  시간 아니면 날짜
                        this.cdate.toLocalDate().toString().equals(LocalDateTime.now().toLocalDate().toString() ) ?
                                this.cdate.toLocalTime().format( DateTimeFormatter.ofPattern( "HH:mm:ss") ) :
                                this.cdate.toLocalDate().format( DateTimeFormatter.ofPattern( "yy-MM-DD") )
                )
                .build();
    }



}