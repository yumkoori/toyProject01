package toyProject.toyProject01.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SpringDataMemberRepository extends JpaRepository <MemberJpaEntity, String> {

    @Query("select m from MemberJpaEntity m where m.email = :email")
    MemberJpaEntity findByMemberEmail(@Param("email") String email);

}
