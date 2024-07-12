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

import java.sql.Date;
import java.util.Optional;
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

    //number로 가져오기
    @Override
    public Member loadMemberWithNumber(Long memberNumber) {

        MemberJpaEntity findMember = memberRepository.findById(memberNumber)
                .orElseThrow(EntityExistsException::new);                          

        return memberMapper.mapToDomainMember(findMember);
    }


    @Override
    public Member loadMemberWithId(String memberId) {

        try {
            MemberJpaEntity findMember = memberRepository.findByMemberId(memberId);        //null일수도 있음.
            log.info("일치하는 회원이 있습니다.");
            return memberMapper.mapToDomainMember(findMember);
        } catch (Exception e) {
            log.info("해당 회원을 찾을 수 없습니다.");
            return null;            //예외로 바꿔야함.
        }
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(memberMapper.mapToJpaEntity(member));
    }
}
