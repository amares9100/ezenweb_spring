package ezenweb.web.domain.board;

import ezenweb.web.domain.member.MemberEntity;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {

    private int rno;

    private String rcontent;

    private int mno;
    private int bno;

    public ReplyEntity toEntity(){
        return ReplyEntity.builder()
                .rno(this.rno)
                .rcontent(this.rcontent)
                .build();
    }

}
