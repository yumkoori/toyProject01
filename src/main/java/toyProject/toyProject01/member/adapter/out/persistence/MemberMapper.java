package toyProject.toyProject01.member.adapter.out.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.domain.Member;

@Component
public class MemberMapper {

    Member mapToDomainMember(MemberJpaEntity memberJpaEntity) {
        return new Member(
                memberJpaEntity.getEmail(),
                memberJpaEntity.getPw(),
                memberJpaEntity.getNickName(),
                memberJpaEntity.getAge(),
                memberJpaEntity.getTel()
        );
    }

    MemberJpaEntity mapToJpaEntity(Member member) {
        return new MemberJpaEntity(
                member.getEmail(),
                member.getPw(),
                member.getNickname(),
                member.getAge(),
                member.getTel()
        );
    }
}
