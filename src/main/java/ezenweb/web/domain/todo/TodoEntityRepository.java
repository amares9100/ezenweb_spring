package ezenweb.web.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoEntityRepository extends JpaRepository<TodoEntity , Integer>{


    Optional<TodoEntity> findAllById(int id);
}
