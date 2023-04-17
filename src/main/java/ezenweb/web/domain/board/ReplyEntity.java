package ezenweb.web.domain.board;

import ezenweb.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;

    @Column
    private String rcontent;


    @ManyToOne // 다수가 하나에게..
    @JoinColumn(name="bno") // Fk 필드명
    @ToString.Exclude // 해당필드는 tostring사용 안함 [ 양방향일때 필수]
    private BoardEntity boardEntity;

    // 작성자fk
    @ManyToOne@JoinColumn(name="mno")@ToString.Exclude
    private MemberEntity memberEntity;

}
