package toyProject.toyProject01.member.adapter.out.persistence;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.application.port.out.DeleteMemberPort;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.application.port.out.SaveMemberPort;
import toyProject.toyProject01.member.application.port.out.UpdateMemberPort;
import toyProject.toyProject01.member.domain.Member;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class MemberJpaAdapter implements
        LoadMemberPort,
        SaveMemberPort,
        UpdateMemberPort,
        DeleteMemberPort {

    private final SpringDataMemberRepository memberRepository;
    private final MemberMapper memberMapper;

    //number로 가져오기
    @Override
    public Member loadMemberWithNumber(Long memberNumber) {

        MemberJpaEntity findMember = memberRepository.findById(memberNumber)
                .orElseThrow(EntityExistsException::new);                            //null일 경우 예외 던짐

        return memberMapper.mapToDomainMember(findMember);
    }

    @Override
    public Member loadMemberWithId(String memberId) {

        MemberJpaEntity findMember = memberRepository.findByMemberId(memberId);        //null일수도 있음.

        if (findMember == null) {
            throw new EntityExistsException("Not found MemberId=" + memberId);
        }

        return memberMapper.mapToDomainMember(findMember);
    }

    @Override
    public void saveMember(RequestJoinDto member) {
        memberRepository.save(memberMapper.mapToJpaEntity(member));
    }
}
