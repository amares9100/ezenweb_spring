package ezenweb.web.domain.board;

import ezenweb.web.domain.member.MemberEntity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "reply")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ReplyEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;

    @Column
    private String rcontent;
    // 게시물fk
    @ManyToOne
    @JoinColumn(name = "bno")
    @ToString.Exclude
    private BoardEntity boardEntity;

    // 작성자fk
    @ManyToOne@JoinColumn(name="mno")
    @ToString.Exclude
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "replyEntity")
    @Builder.Default
    private List<RereplyEntity> rereplyEntitiyList = new ArrayList<>();


    public ReplyDto toDto(){
        return ReplyDto.builder()
                .rno(this.rno)
                .rcontent(this.rcontent)
                .mno(this.memberEntity.getMno())
                .bno(this.boardEntity.getBno())
                .build();
    }



}
