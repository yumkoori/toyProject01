package toyProject.toyProject01.board.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CommentEntity;

import java.util.List;

public interface SpringDataCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT c from CommentEntity " +
            "c LEFT JOIN FETCH c.replies " +
            "LEFT JOIN FETCH c.member " +
            "WHERE c.postId = :postId AND (c.state = :defaultState OR c.state = :deletedParentState) " +
            "ORDER BY c.createTime ASC, c.commentId ASC")
    List<CommentEntity> getComments(
            @Param("postId")Long postId,
            @Param("defaultState") CommentEntity.CommentState state,
            @Param("deletedParentState")CommentEntity.CommentState deletedParent);
}
