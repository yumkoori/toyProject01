package toyProject.toyProject01.member.application.port.in;

import toyProject.toyProject01.member.application.port.in.command.LoginCommand;
import toyProject.toyProject01.member.domain.Member;

public interface MemberLoginUseCase {

    Member Login(LoginCommand loginCommand);

}
