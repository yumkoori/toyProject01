package toyProject.toyProject01.member.application.port.in;

import toyProject.toyProject01.member.application.port.in.command.UpdateCommand;

public interface MemberUpdateUseCase {

    boolean UpdateNickName(UpdateCommand updateCommand);
}
