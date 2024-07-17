package toyProject.toyProject01.member.application.service;
import lombok.Getter;
import toyProject.toyProject01.member.common.ToyProjectErrorCode;

@Getter
public class MemberServiceException extends RuntimeException {

    private final ToyProjectErrorCode errorCode;

    public MemberServiceException(ToyProjectErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MemberServiceException(ToyProjectErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}



