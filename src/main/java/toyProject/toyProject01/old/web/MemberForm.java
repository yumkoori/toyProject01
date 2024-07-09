package toyProject.toyProject01.bord.web;

import lombok.Data;

import java.util.Date;

@Data
public class MemberForm {

    private String id;
    private String pw;
    private String nickname;
    private Date age;
    private String tel;
    private String email;

}
