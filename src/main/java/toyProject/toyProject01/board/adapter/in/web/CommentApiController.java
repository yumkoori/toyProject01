package toyProject.toyProject01.board.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyProject.toyProject01.board.adapter.in.web.dto.RegisterCommentDto;
import toyProject.toyProject01.board.adapter.in.web.dto.ResponseCommentsDto;
import toyProject.toyProject01.board.application.port.in.LoadCommentUseCase;
import toyProject.toyProject01.board.application.port.in.RegisterCommentUseCase;
import toyProject.toyProject01.board.application.port.in.command.RegisterCommentCommand;
import toyProject.toyProject01.common.ResultDto;
import toyProject.toyProject01.common.WebAdapter;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final RegisterCommentUseCase registerCommentUseCase;
    private final LoadCommentUseCase loadCommentUseCase;

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

    @GetMapping(value = "/comments/{postId}")
    public ResponseEntity<ResultDto<List<ResponseCommentsDto>>> getComments(@PathVariable Long postId) {

        List<ResponseCommentsDto> getComments =
                loadCommentUseCase.getComments(postId)
                        .stream()
                        .map(ResponseMapper::mapToCommentsDto)
                        .toList();

        ResultDto<List<ResponseCommentsDto>> result =
                new ResultDto<>(200, "댓글 조회 완료", getComments);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
