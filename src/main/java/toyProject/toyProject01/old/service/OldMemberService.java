package toyProject.toyProject01.old.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toyProject.toyProject01.old.domain.OldMember;
import toyProject.toyProject01.old.mapper.OldMemberMapper;

@Service
public class OldMemberService {

    @Autowired
    private OldMemberMapper oldMemberMapper;

    public void join(OldMember member) {
        oldMemberMapper.save(member);
    }
}

