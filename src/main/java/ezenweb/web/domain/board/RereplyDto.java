package ezenweb.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RereplyDto {

    private int rrno;
    private String rrcontent;
    private int rno;
    private int mno;

    public RereplyEntity toEntity(){
        return RereplyEntity.builder()
                .rrno(this.rrno)
                .rrcontent(this.rrcontent)
                .build();

    }


}
