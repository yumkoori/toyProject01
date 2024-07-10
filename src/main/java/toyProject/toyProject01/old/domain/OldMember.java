package toyProject.toyProject01.old.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OldMember {

    private String id;
    private String pw;
    private String nickname;
    private Date age;
    private String tel;
    private String email;

}
