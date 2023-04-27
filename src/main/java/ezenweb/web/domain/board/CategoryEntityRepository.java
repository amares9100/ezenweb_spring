package ezenweb.web.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Integer> {

}
