package ezenweb.example.day1;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class lomDto {
    private int mno;
    private String mid;
    private String mpassword;
    private long point;
    private String phone;

    public Testmember2 toEntity(){
        return Testmember2.builder()
                .mno(this.mno)
                .mid(this.mid)
                .mpw(this.mpassword)
                .mpoint(this.point)
                .mphone(this.phone)
                .build();
    }
}
