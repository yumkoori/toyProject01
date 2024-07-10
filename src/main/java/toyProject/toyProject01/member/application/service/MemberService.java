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

        try {
            log.info("try 진입");
            Member findMember = loadMemberPort.loadMember(requestMember.getNumber());  //회원 중복 확인

            return false;
        } catch (EntityExistsException e) {
            log.info("예외 잡힘");              //회원 가입 ok
            log.error(e.getMessage());
        }

        log.info("save 통과함.");
        saveMemberPort.saveMember(requestMember);
        return true;
    }




}
