package toyProject.toyProject01.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface SpringDataMemberRepository extends JpaRepository <MemberJpaEntity, Long> {

    @Query("select m from MemberJpaEntity m where m.email = :email")
    Optional<MemberJpaEntity> findByMemberEmail(@Param("email") String email);

    @Modifying
    @Query("update MemberJpaEntity m SET m.nickName = :nickName WHERE m.email = :email")
    void updateNickName(@Param("nickName") String nickName, @Param("email") String email);
}
