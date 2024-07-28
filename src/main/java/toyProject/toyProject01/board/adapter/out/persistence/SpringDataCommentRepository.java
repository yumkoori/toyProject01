package toyProject.toyProject01.board.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CommentEntity;

public interface SpringDataCommentRepository extends JpaRepository<CommentEntity, Long> {

}
