package toyProject.toyProject01.member.application.port.out;

import toyProject.toyProject01.member.domain.Member;

public interface LoadMemberPort {

    //Db에서 가져온 데이터 결과를 도메인 모델로 매핑

    Member loadMember(Long memberNumber);
}
