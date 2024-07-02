package toyProject.toyProject01.bord.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toyProject.toyProject01.bord.domain.Member;
import toyProject.toyProject01.bord.mapper.MemberMapper;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public void join(Member member) {
        memberMapper.save(member);
    }

}
