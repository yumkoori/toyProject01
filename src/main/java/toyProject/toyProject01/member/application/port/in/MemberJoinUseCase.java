package toyProject.toyProject01.member.application.port.in;

import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;

public interface MemberJoinUseCase {

    boolean Join(JoinCommand joinCommand);

    //member domian 수정
}
