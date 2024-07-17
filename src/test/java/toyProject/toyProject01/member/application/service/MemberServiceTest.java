package toyProject.toyProject01.member.application.service;


import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;
import toyProject.toyProject01.member.adapter.out.persistence.SpringDataMemberRepository;
import toyProject.toyProject01.member.application.port.in.MemberJoinUseCase;
import toyProject.toyProject01.member.application.port.in.MemberLoginUseCase;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;
import toyProject.toyProject01.member.application.port.in.command.LoginCommand;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.domain.Member;

import java.sql.Date;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceTest.class);
    @Autowired
    private MemberService memberService;

    @Autowired
    private SpringDataMemberRepository memberRepository;

    @Autowired
    LoadMemberPort loadMemberPort;

    @Autowired
    MemberLoginUseCase memberLoginUseCase;

    @Autowired
    MemberJoinUseCase memberJoinUseCase;

    @BeforeEach
    public void setUp() {
        memberRepository.deleteAll(); // 테스트 실행 전 데이터 초기화

        //회원 테스트용 객체
    }

    @Test
    public void testJoin_Success() {
        // Given
        JoinCommand JoinCommand = new JoinCommand(
                "email",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel"
        );

        // When
        boolean result = memberService.Join(JoinCommand);

        // Then
        assertTrue(result);  // 멤버가 존재하지 않으면 가입 성공

    }

    @Test
    public void testJoin_False() {
        MemberJpaEntity memberJpaEntity = new MemberJpaEntity(
                "email",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel"
        );

        memberRepository.save(memberJpaEntity);

        JoinCommand joinCommand = new JoinCommand(
                "email",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel"
        );

        assertThrows(MemberServiceException.class, ()-> {
            memberService.Join(joinCommand);
        });

    }

    @Test
    public void testLogin_Success() {
        //given
        JoinCommand joinMember = new JoinCommand(
                "email",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel"
        );

        memberJoinUseCase.Join(joinMember);
        LoginCommand loginMember = new LoginCommand("email", "1234");

        //when
        Member loginResult = memberLoginUseCase.Login(loginMember);

        //then
        Assertions.assertThat(loginResult).isNotNull();

    }

    @Test
    @DisplayName("email은 일치하지만, pw가 틀릴때")
    public void testLogin_false_pw() {
        //given
        JoinCommand joinMember = new JoinCommand(
                "email",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel"
        );
        memberJoinUseCase.Join(joinMember);
        log.info("회원가입 완료");

        //when
        LoginCommand loginMember = new LoginCommand("email", "78910");

        //then
        assertThrows(MemberServiceException.class, ()-> {
            memberLoginUseCase.Login(loginMember);
        });
    }

    @Test
    @DisplayName("존재하지 않는 email을 입력했을때")
    public void testLogin_false_id() {
        //given
        LoginCommand loginMember = new LoginCommand("email", "78910");

        //when

        //then
        assertThrows(MemberServiceException.class, ()-> {
            memberLoginUseCase.Login(loginMember);
        });
    }
}