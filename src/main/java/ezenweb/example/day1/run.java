package ezenweb.example.day1;

public class run {
    public static void main(String[] args) {
        // 1. lombok dto @AllArgsConstructor // 풀생성자 생성
        lomDto dto = new lomDto(1, "qwe" , "qwe" , 100 , "010-1111-1111");

        // 2. lombok dto @NoArgsConstructor // 빈생성자 생성
        lomDto dto1 = new lomDto();

        // 3. lombok @Getter,@Setter // 겟셋
        System.out.println("getter" + dto1.getMid());
        dto1.setMid("asd");

        // 4. lombok tostring
        System.out.println("dto : " + dto.toString());

        // 5. lombok @Builder  //원하는 생성자 세팅
        lomDto dto2 = lomDto.builder()
                .mno(2)
                .point(100)
                .mid("asdasd")
                .mpassword("asdasd")
                .build();
        // 생성자 사용하지않고 원하는 값이 저장된 객체 생성
        // 객체 생성시 매개변수 개수 상관x  순서상관x
        System.out.println("dto2 : " + dto2.toString());


        dto2.setPhone("010-2222-2222");
        System.out.println("dto2 : " + dto2.toString());
    }
}
