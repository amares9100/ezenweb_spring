package ezenweb.web.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Table(name="bcategory")
@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    @Column
    private String cname;

    @OneToMany(mappedBy = "CategoryEntity") // pk --> fk
    @Builder.Default
    private List<BoardEntity> boardEntityList = new ArrayList<>();

}
