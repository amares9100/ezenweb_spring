package ezenweb.example.day1;

import lombok.Builder;

import javax.persistence.*;

// JPA : 자바 퍼시스턴스 API - 관계형데이터베이스를 객체로 표현 -
@Entity // Spring Data JPA 필요 // DB테이블 매핑
@Table(name = "testmember2") // 생략시 클래스명으로 테이블명 생성
@Builder
public class Testmember2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    @Column
    private String mid;
    @Column
    private  String mpw;
    @Column
    private long mpoint;
    @Column
    private String mphone;
}
