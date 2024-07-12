package toyProject.toyProject01.member.adapter.in.web;

import lombok.Data;

import java.util.Date;

@Data
public class RequestJoinDto {

    private Long number;
    private String memberId;
    private String pw;
    private String nickname;
    private Date age;
    private String tel;
    private String email;

//    public RequestJoinDto(Long number, String memberId, String pw, String nickname, Date age, String tel, String email) {
//        this.number = number;
//        this.memberId = memberId;
//        this.pw = pw;
//        this.nickname = nickname;
//        this.age = age;
//        this.tel = tel;
//        this.email = email;
//    }
}
