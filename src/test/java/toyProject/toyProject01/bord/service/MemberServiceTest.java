package toyProject.toyProject01.bord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import toyProject.toyProject01.bord.domain.Member;
import toyProject.toyProject01.bord.service.MemberService;

import java.sql.Date;

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testInsertMember() {
        Member member = new Member();
        member.setId("user3");
        member.setPw("password1");
        member.setNickname("nickname1");
        member.setAge(Date.valueOf("1990-01-01"));
        member.setTel("010-1234-5678");
        member.setEmail("user1@example.com");

        memberService.join(member);
    }



}