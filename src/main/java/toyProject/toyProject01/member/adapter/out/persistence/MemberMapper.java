package toyProject.toyProject01.member.adapter.out.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.domain.Member;

@Component
public class MemberMapper {

    Member mapToDomainMember(MemberJpaEntity memberJpaEntity) {
        return new Member(
                memberJpaEntity.getNumber(),
                memberJpaEntity.getMemberId(),
                memberJpaEntity.getPw(),
                memberJpaEntity.getNickName(),
                memberJpaEntity.getAge(),
                memberJpaEntity.getTel(),
                memberJpaEntity.getEmail()
        );
    }

    MemberJpaEntity mapToJpaEntity(Member member) {
        return new MemberJpaEntity(
                member.getNumber(),
                member.getMemberId(),
                member.getPw(),
                member.getNickname(),
                member.getAge(),
                member.getTel(),
                member.getEmail()
        );
    }
}
