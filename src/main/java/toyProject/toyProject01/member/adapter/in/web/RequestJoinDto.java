package toyProject.toyProject01.member.adapter.in.web;

import lombok.Data;

import java.util.Date;

@Data
public class RequestJoinDto {

    //검증 필요

    private String email;
    private String pw;
    private String nickname;
    private Date age;
    private String tel;


}
