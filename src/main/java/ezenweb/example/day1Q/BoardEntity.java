package ezenweb.example.day1Q;

import lombok.*;

import javax.persistence.*;

@Entity@Table(name="testboard")
@Setter@Getter@ToString
@AllArgsConstructor @NoArgsConstructor
@Builder

public class BoardEntity {
    @Id // pk선언 : 생략시 오류발생
    @GeneratedValue (strategy = GenerationType.IDENTITY) // auto키
    private int bno;
    @Column // 필드선언
    private  String btitle;
    @Column // 필드선언
    private  String bcontent;


}
