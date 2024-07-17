package toyProject.toyProject01.member.common;

import lombok.Getter;

@Getter
public enum ToyProjectErrorCode {

    //10xx Common

    //11xx Member
    EMAIL_DUPLICATION(1100,"이미 존재하는 이메일"),
    EMAIL_NOT_EXISTENCE(1101,  "존재하지 않는 이메일"),
    LOGIN_PW_WRONG(1102,  "일치하지 않는 비밀번호"),
    ;

    //12xx Board

    private final Integer code;
    private final String message;

    ToyProjectErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}