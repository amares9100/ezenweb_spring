package ezenweb.example.day1;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Testmember2repository extends JpaRepository<Testmember2 , Integer> {


}
