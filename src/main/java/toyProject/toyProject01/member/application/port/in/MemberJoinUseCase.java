package toyProject.toyProject01.member.application.port.in;

import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;

public interface MemberJoinUseCase {

    //외부에서 회원가입 유스케이스 사용하기위해 서비스에 접근하기 위해 필요한 포트 -> MemberService가 구현함


    public boolean Join(RequestJoinDto requestJoinDto);

}
