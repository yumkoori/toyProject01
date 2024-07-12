package toyProject.toyProject01.member.application.port.in.command;

import lombok.Data;

import java.util.Date;

@Data
public class JoinCommand {

    private final String memberId;
    private final String pw;
    private final String nickname;
    private final Date age;
    private final String tel;
    private final String email;

}
