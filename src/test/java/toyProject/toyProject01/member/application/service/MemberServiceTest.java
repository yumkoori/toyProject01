package toyProject.toyProject01.member.application.service;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;
import toyProject.toyProject01.member.adapter.out.persistence.SpringDataMemberRepository;
import toyProject.toyProject01.member.application.port.in.MemberJoinUseCase;
import toyProject.toyProject01.member.application.port.in.MemberLoginUseCase;
import toyProject.toyProject01.member.application.port.in.MemberUpdateUseCase;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;
import toyProject.toyProject01.member.application.port.in.command.LoginCommand;
import toyProject.toyProject01.member.application.port.in.command.UpdateCommand;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.application.port.out.UpdateMemberPort;

import java.sql.Date;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

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

    @Autowired
    MemberUpdateUseCase memberUpdateUseCase;

    @BeforeEach
    public void setUp() {
        memberRepository.deleteAll(); // 테스트 실행 전 데이터 초기화

        //회원 테스트용 객체
    }

    @Test
    public void testJoin_Success() {
        // Given
        JoinCommand JoinCommand = new JoinCommand(
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

        memberRepository.save(memberJpaEntity);

        JoinCommand joinCommand = new JoinCommand(
                "yumi",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel",
                "email"
        );

        boolean result = memberService.Join(joinCommand);
        assertFalse(result);
    }

    @Test
    public void testLogin_Success() {
        //given
        JoinCommand joinMember = new JoinCommand(
                "yumi",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel",
                "email"
        );

        memberJoinUseCase.Join(joinMember);
        LoginCommand loginMember = new LoginCommand("yumi", "1234");

        //when
        boolean loginResult = memberLoginUseCase.Login(loginMember);

        //then
        Assertions.assertThat(loginResult).isTrue();

    }

    @Test
    @DisplayName("id는 일치하지만, pw가 틀릴때")
    public void testLogin_false_pw() {
        //given
        JoinCommand joinMember = new JoinCommand(
                "yumi",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel",
                "email"
        );
        memberJoinUseCase.Join(joinMember);

        //when
        LoginCommand loginMember = new LoginCommand("yumi", "78910");
        boolean loginResult = memberLoginUseCase.Login(loginMember);

        //then
        Assertions.assertThat(loginResult).isFalse();
    }

    @Test
    @DisplayName("존재하지 않는 id를 입력했을때")
    public void testLogin_false_id() {
        //given

        //when
        LoginCommand loginMember = new LoginCommand("yumi", "78910");
        boolean loginResult = memberLoginUseCase.Login(loginMember);

        //then
        Assertions.assertThat(loginResult).isFalse();
    }


    @Test
    public void testUpdate_Success_nickName() {
        //given
        JoinCommand joinMember = new JoinCommand(
                "yumi",
                "1234",
                "test",
                Date.valueOf("1990-01-01"),
                "tel",
                "email"
        );

        memberJoinUseCase.Join(joinMember);

        UpdateCommand updateCommand = new UpdateCommand("yumi", "coco");

        //when
        boolean result = memberUpdateUseCase.UpdateNickName(updateCommand);

        //then
        Assertions.assertThat(result).isTrue();
    }
}