package toyProject.toyProject01.member.application.port.in.command;

import lombok.Data;

@Data
public class LoginCommand {
    private final String memberId;
    private final String pw;
}
