package toyProject.toyProject01.member.application.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toyProject.toyProject01.member.adapter.in.web.RequestJoinDto;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;
import toyProject.toyProject01.member.adapter.out.persistence.MemberMapper;
import toyProject.toyProject01.member.adapter.out.persistence.SpringDataMemberRepository;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;
import toyProject.toyProject01.member.domain.Member;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private SpringDataMemberRepository memberJpaRepository;

    @Autowired
    private MemberMapper memberMapper;

    @Before
    public void setUp() {
        memberJpaRepository.deleteAll();  // 테스트 전 데이터베이스 초기화
    }

    @Test
    public void testJoin_Success() {
        // Given
        JoinCommand JoinCommand = new JoinCommand(
                6L,
                "yumi",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel",
                "email"
        );

        // When
        boolean result = memberService.Join(JoinCommand);

        // Then
        assertTrue(result);  // 멤버가 존재하지 않으면 가입 성공

    }

    @Test
    public void testJoin_False() {
        MemberJpaEntity memberJpaEntity = new MemberJpaEntity(
                6L,
                "yumi",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel",
                "email"
        );

        JoinCommand joinCommand = new JoinCommand(
                6L,
                "yumi",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel",
                "email"
        );

        memberJpaRepository.save(memberJpaEntity);

        boolean result = memberService.Join(joinCommand);
        assertFalse(result);
    }
}