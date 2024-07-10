package toyProject.toyProject01.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMemberRepository extends JpaRepository <MemberJpaEntity, Long> {
}
