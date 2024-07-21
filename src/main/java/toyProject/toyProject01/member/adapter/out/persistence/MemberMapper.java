package toyProject.toyProject01.member.adapter.out.persistence;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.domain.Member;

@Component
@Getter
public class MemberMapper {

    static public Member mapToDomainMember(MemberJpaEntity memberJpaEntity) {
        return new Member(

                memberJpaEntity.getEmail(),
                memberJpaEntity.getPw(),
                memberJpaEntity.getNickName(),
                memberJpaEntity.getAge(),
                memberJpaEntity.getTel()
        );
    }

    static public MemberJpaEntity mapToJpaEntity(Member member) {
        return new MemberJpaEntity(
                null,
                member.getEmail(),
                member.getPw(),
                member.getNickname(),
                member.getAge(),
                member.getTel()
        );
    }
}
