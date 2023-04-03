package ezenweb.example.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public Dao(){
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root" , "1234");
       }catch (Exception e){
           System.out.println("연동실패 : " + e);
       }

    }

    public boolean setmember(lomDto dto){
        String sql = "insert into testmember values(?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1 , dto.getMno());
            ps.setString(2 , dto.getMid());
            ps.setString(3 , dto.getMpassword());
            ps.setLong(4,dto.getPoint());
            ps.setString(5,dto.getPhone());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
            return false;
    }
}
