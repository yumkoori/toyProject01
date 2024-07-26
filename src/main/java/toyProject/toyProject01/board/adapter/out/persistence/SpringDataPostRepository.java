package toyProject.toyProject01.board.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;

import java.util.Optional;

public interface SpringDataPostRepository extends JpaRepository<PostJpaEntity, Long> {

    @Query("select p from PostJpaEntity p where p.postState = :postState")
    Page<PostJpaEntity> getPosts(Pageable pageable, PostJpaEntity.PostState postState);

    @Query("select p from PostJpaEntity p where p.postId = :postId AND p.postState = :postState")
    Optional<PostJpaEntity> getPostDetail(Long postId, PostJpaEntity.PostState postState);
}
