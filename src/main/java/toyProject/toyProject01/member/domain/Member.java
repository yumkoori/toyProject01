package toyProject.toyProject01.member.domain;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Data
public class Member {

    //비즈니스 엔티티

    private final String memberId;
    private final String pw;
    private final String nickname;
    private final Date age;
    private final String tel;
    private final String email;

    public Member(String memberId, String pw, String nickname, Date age, String tel, String email) {

        this.memberId = memberId;
        this.pw = pw;
        this.nickname = nickname;
        this.age = age;
        this.tel = tel;
        this.email = email;
    }

    public boolean isSamePw (String pw) {

        return pw.equals(this.pw);          //일치하면 true, 틀리면 false;
    }




    public static Member mapToMember(JoinCommand joinCommand) {
        return new Member(joinCommand.getMemberId(),
                joinCommand.getPw(),
                joinCommand.getNickname(),
                joinCommand.getAge(),
                joinCommand.getTel(),
                joinCommand.getEmail()
        );
    };


}
