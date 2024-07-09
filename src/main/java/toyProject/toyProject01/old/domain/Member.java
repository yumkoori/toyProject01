package toyProject.toyProject01.bord.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Member {

    private String id;
    private String pw;
    private String nickname;
    private Date age;
    private String tel;
    private String email;

}
