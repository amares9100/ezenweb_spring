package ezenweb.example.day1;

public class run2 {
    public static void main(String[] args) {
        //
        lomDto dto = lomDto.builder()
                .mno(1).mid("asd").mpassword("asd").point(1000).phone("010-3333-3333")
                .build();

        System.out.println("dto : " + dto.toString());

        System.out.println("한글~~");

        Dao dao = new Dao();

        boolean result = dao.setmember(dto);


    }
}
