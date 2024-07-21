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
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class MemberJpaAdapter implements
        LoadMemberPort,
        SaveMemberPort,
        UpdateMemberPort,
        DeleteMemberPort {

    private final SpringDataMemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Member loadMemberWithEmail(String email) {

        //조회되는 값이 없으면 Optional.empty()가 반환되고, 예외 반환
        MemberJpaEntity memberJpaEntity = memberRepository.findByMemberEmail(email)
               .orElseThrow(() -> new NoSuchElementException("해당 회원을 찾을 수 없습니다."));

        return MemberMapper.mapToDomainMember(memberJpaEntity);
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(MemberMapper.mapToJpaEntity(member));
    }

    @Override
    public void updateNickName(String email, String nickName) {
        memberRepository.updateNickName(nickName, email);
    }


}
