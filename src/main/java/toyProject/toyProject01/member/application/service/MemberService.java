package toyProject.toyProject01.member.application.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.application.port.in.MemberJoinUseCase;
import toyProject.toyProject01.member.application.port.in.MemberLoginUseCase;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;
import toyProject.toyProject01.member.application.port.in.command.LoginCommand;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.application.port.out.SaveMemberPort;
import toyProject.toyProject01.member.domain.Member;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService implements MemberJoinUseCase, MemberLoginUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;

    //밖에서 어떤 구현체들의 사항이 바뀌어도 이 service의 코드는 바뀌지 않아야함.
    @Override
    public boolean Join(JoinCommand joinCommand) {              //Command로 검증된 request 객체

            //memberId로 회원 조회
            Member findMember = loadMemberPort.loadMemberWithId(joinCommand.getMemberId());

            if(findMember == null) {
                log.info("중복되는 회원이 없습니다. 회원가입이 가능합니다.");

                Member member = Member.mapToMember(joinCommand);
                saveMemberPort.saveMember(member);

                return true;
            }

            log.info("이미 존재하는 회원입니다. ");
            return false;
    }


    @Override
    public boolean Login(LoginCommand loginCommand) {

        Member findmember = loadMemberPort.loadMemberWithId(loginCommand.getMemberId());
        
        if(findmember == null) {
            log.info("존재하는 회원이 없습니다. 로그인을 할 수 없습니다.");
            return false;
        } else {
            log.info("존재하는 아이디가 있습니다.");
            boolean loginResult = findmember.isSamePw(loginCommand.getPw());

            if (loginResult) {
                log.info("pw도 일치합니다.");
                return true;
            } else {
                log.info("id는 일치하지만, pw가 틀립니다.");
                return false;
            }
        }
    }
}
