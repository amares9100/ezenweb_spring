package ezenweb.web.domain.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "todo")
public class TodoEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    @Column
    private String title;
    @ColumnDefault("1")
    private boolean done;

    public TodoDto toDto(){
        return TodoDto.builder()
                .id(this.id)
                .title(this.title)
                .done(this.done)
                .build();

    }
}
