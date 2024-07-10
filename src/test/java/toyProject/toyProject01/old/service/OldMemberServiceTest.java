package toyProject.toyProject01.old.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import toyProject.toyProject01.old.domain.OldMember;

import java.sql.Date;

@SpringBootTest
@ActiveProfiles("test")
class OldMemberServiceTest {

    @Autowired
    private OldMemberService oldMemberService;

    @Test
    public void testInsertMember() {
        OldMember member = new OldMember();
        member.setId("user3");
        member.setPw("password1");
        member.setNickname("nickname1");
        member.setAge(Date.valueOf("1990-01-01"));
        member.setTel("010-1234-5678");
        member.setEmail("user1@example.com");

        oldMemberService.join(member);
    }



}