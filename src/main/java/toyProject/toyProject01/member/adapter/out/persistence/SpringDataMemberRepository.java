package toyProject.toyProject01.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SpringDataMemberRepository extends JpaRepository <MemberJpaEntity, Long> {

    @Query("select m from MemberJpaEntity m where m.memberId = :memberId")
    MemberJpaEntity findByMemberId(@Param("memberId") String memberId);

    @Modifying
    @Query("update MemberJpaEntity m SET m.nickName = :nickName WHERE m.memberId = :memberId")
    void updateNickName(@Param("nickName") String nickName, @Param("memberId") String memberId);
}
