package ezenweb.web.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgEntityRepository extends JpaRepository<ProductImgEntity , Integer> {

}
