package toyProject.toyProject01.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface SpringDataMemberRepository extends JpaRepository <MemberJpaEntity, String> {

    @Query("select m from MemberJpaEntity m where m.email = :email")
    Optional<MemberJpaEntity> findByMemberEmail(@Param("email") String email);

}
