package toyProject.toyProject01.member.application.port.in.command;

import lombok.Data;

@Data
public class LoginCommand {

    private final String email;
    private final String pw;
}
