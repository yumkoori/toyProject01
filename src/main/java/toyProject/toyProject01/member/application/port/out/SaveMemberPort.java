package toyProject.toyProject01.member.application.port.out;

import org.apache.coyote.Request;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.domain.Member;

public interface SaveMemberPort {

    void saveMember(RequestJoinDto member);

    //검증이 끝난 MemberPort이후에, Member DB에 저장.
}
