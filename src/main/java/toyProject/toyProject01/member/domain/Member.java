package toyProject.toyProject01.member.domain;

import lombok.Getter;

import java.security.MessageDigest;
import java.util.Date;

@Getter
public class Member {

    //비즈니스 엔티티
    private final Long Number;
    private final String memberId;
    private final String pw;
    private final String nickname;
    private final Date age;
    private final String tel;
    private final String email;

    public Member(Long memberNumber, String memberId, String pw, String nickname, Date age, String tel, String email) {
        this.Number = memberNumber;
        this.memberId = memberId;
        this.pw = pw;
        this.nickname = nickname;
        this.age = age;
        this.tel = tel;
        this.email = email;
    }

    //비즈니스 로직
    //회원가입
    //로그인



}
