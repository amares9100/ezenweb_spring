package ezenweb.example.day02.controller;

import lombok.*;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Builder
public class ParamDto {
    private String param1;
    private String param2;

    @Override
    public String toString() {
        return "ParamDto{" +
                "param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                '}';
    }
}
