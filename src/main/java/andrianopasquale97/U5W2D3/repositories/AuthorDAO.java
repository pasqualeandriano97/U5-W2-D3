package andrianopasquale97.U5W2D3.repositories;

import andrianopasquale97.U5W2D3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorDAO extends JpaRepository<Author, Integer> {
    boolean existsByEmail(String email);
    Optional<Author> findByEmail(String email);
}
