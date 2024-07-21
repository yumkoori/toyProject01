package toyProject.toyProject01.board.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;

public interface SpringDataPostRepository extends JpaRepository<PostJpaEntity, Long> {
}
