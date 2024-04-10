package andrianopasquale97.U5W2D3.repositories;

import andrianopasquale97.U5W2D3.entities.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostDAO extends JpaRepository<Blogpost, Integer> {
}
