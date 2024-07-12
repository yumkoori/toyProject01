package toyProject.toyProject01.member.application.port.in;

import toyProject.toyProject01.member.application.port.in.command.LoginCommand;

public interface MemberLoginUseCase {

    boolean Login(LoginCommand loginCommand);

}
