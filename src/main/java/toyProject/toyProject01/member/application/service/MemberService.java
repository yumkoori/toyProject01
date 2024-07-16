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

import java.util.NoSuchElementException;

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

        if (possibleJoinWithEmail(joinCommand.getEmail())) {

            Member member = Member.mapToMember(joinCommand);
            saveMemberPort.saveMember(member);

            return true;

        } else {
            throw new MemberServiceException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public boolean Login(LoginCommand loginCommand) {

        try {
            Member findMember = loadMemberPort.loadMemberWithEmail(loginCommand.getEmail());

            log.info("가입된 이메일이 있습니다.");

            if(!findMember.isSamePw(loginCommand.getPw())) {
                throw new MemberServiceException("가입된 이메일의 비밀번호가 틀립니다.");
            }

        } catch (NoSuchElementException e) {
            throw new MemberServiceException("가입된 이메일이 없습니다.");
        }

        log.info("로그인이 가능합니다.");
        return true;
    }

    private boolean possibleJoinWithEmail(String email) {
        try {
            Member findMember = loadMemberPort.loadMemberWithEmail(email);

            if(findMember != null) {
                log.info("존재하는 이메일 입니다.");
            }

        } catch (NoSuchElementException e) {
            log.info("중복되는 회원이 없습니다 회원가입이 가능합니다.", e);
            return true;
        }

        return false;
    }

}
