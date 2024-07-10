package toyProject.toyProject01.member.application.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.application.port.in.MemberJoinUseCase;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.application.port.out.SaveMemberPort;
import toyProject.toyProject01.member.domain.Member;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService implements MemberJoinUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;

    @Override
    public boolean Join(RequestJoinDto requestMember) {

        //memberId로 회원 조회
        Member findMember = loadMemberPort.loadMemberWithId(requestMember.getMemberId());



        //이거 도메인 비즈니스 로직으로 빼야됌.
        if(findMember.getMemberId().equals(requestMember.getMemberId())) {
            log.info("이미 존재하는 회원입니다. ");
            return false;
        }

        //이거 Member로 넣어주는게 맞음. 도메인에서 매핑하던지 어디서 매핑해서 request-> member로
        saveMemberPort.saveMember(requestMember);
        return true;
    }




}
