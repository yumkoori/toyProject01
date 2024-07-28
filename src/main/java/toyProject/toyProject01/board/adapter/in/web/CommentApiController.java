package toyProject.toyProject01.board.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyProject.toyProject01.board.adapter.in.web.dto.RegisterCommentDto;
import toyProject.toyProject01.board.application.port.in.RegisterCommentUseCase;
import toyProject.toyProject01.board.application.port.in.command.RegisterCommentCommand;
import toyProject.toyProject01.common.ResultDto;
import toyProject.toyProject01.common.WebAdapter;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final RegisterCommentUseCase registerCommentUseCase;

    @PostMapping(value = "/comments")
    public ResponseEntity<ResultDto<String>> RegisterComment(@RequestBody RegisterCommentDto request) {
        RegisterCommentCommand command = new RegisterCommentCommand(
                request.getPostId(),
                request.getNickName(),
                request.getContent(),
                request.getParentId()
        );

        registerCommentUseCase.registerComment(command);

        ResultDto<String> result = new ResultDto<>(200, "저장 완료", "ok");

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
