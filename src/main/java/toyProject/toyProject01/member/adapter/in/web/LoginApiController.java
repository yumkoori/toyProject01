package toyProject.toyProject01.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyProject.toyProject01.member.application.port.in.MemberLoginUseCase;
import toyProject.toyProject01.member.application.port.in.command.LoginCommand;
import toyProject.toyProject01.member.application.service.MemberServiceException;
import toyProject.toyProject01.member.common.ResultDto;
import toyProject.toyProject01.member.common.ToyProjectErrorCode;
import toyProject.toyProject01.member.domain.Member;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final MemberLoginUseCase memberLoginUseCase;

    @PostMapping(value =  "/members/login")
    public ResponseEntity<ResultDto<ResponseMemberDto>> join(@RequestBody RequestJoinDto request) {

        log.info("messageBody={}", request);

        LoginCommand loginCommand = new LoginCommand(
                request.getEmail(),
                request.getPw()
        );

        Member loginMember = memberLoginUseCase.Login(loginCommand);

        ResponseMemberDto responseDto = ResponseMemberDto.mapToResponseDto(loginMember.getNickname());

        ResultDto<ResponseMemberDto> result =
                new ResultDto<>(200, "로그인 검증 성공", responseDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ExceptionHandler(MemberServiceException.class)
    public ResponseEntity<ResultDto<String>> memberExHandle(MemberServiceException e) {
        log.error(e.getMessage());

        ResultDto<String> result = null;

        //존재하지 않는 email의 로그인 시도의 에러
        if(e.getErrorCode().equals(ToyProjectErrorCode.EMAIL_NOT_EXISTENCE)) {
            result = new ResultDto<>(
                    ToyProjectErrorCode.EMAIL_NOT_EXISTENCE.getCode(),
                    ToyProjectErrorCode.EMAIL_NOT_EXISTENCE.getMessage(),
                    null
            );
        }

        //잘못된 비밀번호 입력시의 에러
        if (e.getErrorCode().equals(ToyProjectErrorCode.LOGIN_PW_WRONG)){
            result = new ResultDto<>(
                    ToyProjectErrorCode.LOGIN_PW_WRONG.getCode(),
                    ToyProjectErrorCode.LOGIN_PW_WRONG.getMessage(),
                    null
            );
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
