package ezenweb.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDto {

    private long totalCount;
    private int totalPage;
    private List<BoardDto> boardDtoList;
    private int page;
    private int cno;

    private String key;
    private String keyword;

}
