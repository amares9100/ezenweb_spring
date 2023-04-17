package ezenweb.web.domain.board;

import ezenweb.web.domain.member.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="board")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;
    @Column
    private String btitle;
    @Column
    private String bcontent;
    // Fk 가져오기
    @ManyToOne // 다수가 하나에게..
    @JoinColumn(name="cno") // Fk 필드명
    @ToString.Exclude // 해당필드는 tostring사용 안함 [ 양방향일때 필수]
    private CategoryEntity CategoryEntity;

    @ManyToOne
    @JoinColumn(name="mno")
    @ToString.Exclude // 해당필드는 tostring사용 안함 [ 양방향일때 필수]
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity")
    @Builder.Default
    private List<ReplyEntity> ReplyEntityList = new ArrayList<>();



}
