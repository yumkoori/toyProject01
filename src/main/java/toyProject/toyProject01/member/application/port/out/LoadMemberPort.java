package toyProject.toyProject01.member.application.port.out;

import toyProject.toyProject01.member.domain.Member;

public interface LoadMemberPort {

    Member loadMemberWithNumber(Long memberNumber);

    Member loadMemberWithId(String memberId);
}
