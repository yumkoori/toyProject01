package toyProject.toyProject01.member.common;

import lombok.Getter;

@Getter
public enum ToyProjectErrorCode {

    //10xx Common

    //11xx Member
    EMAIL_DUPLICATION(1100, "M001", "이미 존재하는 이메일"),
    EMAIL_NOT_EXISTENCE(1101, "MOO2", "존재하지 않는 이메일"),
    LOGIN_PW_WRONG(1003, "MOO3", "일치하지 않는 비밀번호"),
    ;

    //12xx Board

    private final Integer status;
    private final String code;
    private final String message;

    ToyProjectErrorCode(Integer status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}