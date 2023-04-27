package ezenweb.web.domain.board;

import ezenweb.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "rereply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RereplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rrno;

    @Column
    private String rrcontent;

    @ManyToOne
    @JoinColumn(name = "rno")
    @ToString.Exclude
    private ReplyEntity replyEntity;

    @ManyToOne@JoinColumn(name="mno")
    @ToString.Exclude
    private MemberEntity memberEntity;

    public RereplyDto toDto(){
        return RereplyDto.builder()
                .rrno(this.rrno)
                .rrcontent(this.rrcontent)
                .rno(this.replyEntity.getRno())
                .mno(this.memberEntity.getMno())
                .build();

    }

}
