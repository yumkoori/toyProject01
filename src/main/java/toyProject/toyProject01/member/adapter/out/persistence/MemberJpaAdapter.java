package toyProject.toyProject01.member.adapter.out.persistence;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.application.port.out.DeleteMemberPort;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.application.port.out.SaveMemberPort;
import toyProject.toyProject01.member.application.port.out.UpdateMemberPort;
import toyProject.toyProject01.member.domain.Member;

@Component
@RequiredArgsConstructor
public class MemberJpaAdapter implements
        LoadMemberPort,
        SaveMemberPort,
        UpdateMemberPort,
        DeleteMemberPort {

    private static final Logger log = LoggerFactory.getLogger(MemberJpaAdapter.class);
    private final SpringDataMemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member loadMemberWithEmail(String email) {

        try {
            MemberJpaEntity findMember = memberRepository.findByMemberEmail(email);        //null일수도 있음.
            return memberMapper.mapToDomainMember(findMember);
        } catch (Exception e) {
            log.info("해당 회원을 찾을 수 없습니다. " );
            log.info("error", e);
            return null;            //예외로 바꿔야함.
        }
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(memberMapper.mapToJpaEntity(member));
    }
}
