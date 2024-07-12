package toyProject.toyProject01.member.application.port.in.command;

import lombok.Data;

import java.util.Date;

@Data
public class JoinCommand {

    private final Long memberNumber;
    private final String memberId;
    private final String pw;
    private final String nickname;
    private final Date age;
    private final String tel;
    private final String email;


//    public JoinCommand(Long memberNumber, String memberId, String pw, String nickname, Date age, String tel, String email) {
//        this.memberNumber = memberNumber;
//        this.memberId = memberId;
//        this.pw = pw;
//        this.nickname = nickname;
//        this.age = age;
//        this.tel = tel;
//        this.email = email;
//    }
}
