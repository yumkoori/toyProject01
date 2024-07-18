package toyProject.toyProject01.member.application.service;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.application.port.in.MemberJoinUseCase;
import toyProject.toyProject01.member.application.port.in.MemberLoginUseCase;
import toyProject.toyProject01.member.application.port.in.MemberUpdateUseCase;
import toyProject.toyProject01.member.application.port.in.command.UpdateCommand;
import toyProject.toyProject01.member.application.port.out.UpdateMemberPort;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;
import toyProject.toyProject01.member.application.port.in.command.LoginCommand;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.application.port.out.SaveMemberPort;
import toyProject.toyProject01.member.common.ToyProjectErrorCode;
import toyProject.toyProject01.member.domain.Member;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class MemberService implements MemberJoinUseCase, MemberLoginUseCase, MemberUpdateUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;
    private final UpdateMemberPort updateMemberPort;

    //밖에서 어떤 구현체들의 사항이 바뀌어도 이 service의 코드는 바뀌지 않아야함.
    @Override
    public boolean Join(JoinCommand joinCommand) {              //Command로 검증된 request 객체

        if (possibleJoinWithEmail(joinCommand.getEmail())) {

            Member member = Member.mapToMember(joinCommand);
            saveMemberPort.saveMember(member);

            return true;

        } else {
            throw new MemberServiceException(
                    ToyProjectErrorCode.EMAIL_DUPLICATION,
                    "이미 가입된 회원입니다."
            );
        }
    }

    @Override
    public Member Login(LoginCommand loginCommand) {

        try {
            Member findMember = loadMemberPort.loadMemberWithEmail(loginCommand.getEmail());

            log.info("가입된 이메일이 있습니다.");

            if(!findMember.isSamePw(loginCommand.getPw())) {
                throw new MemberServiceException(
                        ToyProjectErrorCode.LOGIN_PW_WRONG, "가입된 이메일의 비밀번호가 틀립니다."
                );
            }

            //이메일, pw 모두 일치하면 회원 정보 반환
            return findMember;

        } catch (NoSuchElementException e) {
            throw new MemberServiceException(ToyProjectErrorCode.EMAIL_NOT_EXISTENCE,
                    "가입된 이메일이 없습니다.",
                    e
            );
        }
    }
      
    @Override
    public boolean UpdateNickName(UpdateCommand updateCommand) {

        updateMemberPort.updateNickName(updateCommand.getEmail(), updateCommand.getNickname());

        log.info("입력된 닉네임: = {}", updateCommand.getNickname());
        Member findmember = loadMemberPort.loadMemberWithEmail(updateCommand.getEmail());

        log.info("변경된 닉네임 = {}", findmember.getNickname());

        if (findmember.isSameNickName(updateCommand.getNickname())) {
            log.info("변경이 완료 됐습니다.");
            return true;
        } else {
            log.info("변경에 문제가 있습니다");
            return false;
        }
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
