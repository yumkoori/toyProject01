package toyProject.toyProject01.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyProject.toyProject01.member.application.port.in.MemberJoinUseCase;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;
import toyProject.toyProject01.member.common.ResultDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberJoinUseCase memberJoinUseCase;

    @PostMapping(value =  "/members/join")
    public ResponseEntity<ResultDto<ResponseMemberDto>> join(@RequestBody RequestJoinDto request) {

        log.info("messageBody={}", request);

        JoinCommand joinCommand = new JoinCommand(
                request.getEmail(),
                request.getPw(),
                request.getNickname(),
                request.getAge(),
                request.getTel()
        );

        boolean joinResult = memberJoinUseCase.Join(joinCommand);

        if(joinResult) {
            ResponseMemberDto responseDto = ResponseMemberDto.mapToResponseDto(joinCommand.getNickname());

            ResultDto<ResponseMemberDto> result =
                    new ResultDto<>(200, "회원가입 완료", responseDto);

            return new ResponseEntity<>(result, HttpStatus.CREATED);

        } else {
            ResultDto<ResponseMemberDto> result =
                    new ResultDto<>(400, "회원가입 실패",  new ResponseMemberDto("BadRequest"));

            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

    }
}
