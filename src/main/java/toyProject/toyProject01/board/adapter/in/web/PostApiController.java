package toyProject.toyProject01.board.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyProject.toyProject01.board.adapter.in.web.dto.ResisterPostDto;
import toyProject.toyProject01.board.adapter.in.web.dto.ResponsePostDto;
import toyProject.toyProject01.board.application.port.in.ResisterPostUseCase;
import toyProject.toyProject01.board.application.port.in.command.ResisterPostCommand;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.ResultDto;
import toyProject.toyProject01.common.WebAdapter;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final ResisterPostUseCase resisterPostUseCase;

    @PostMapping(value = "/posts")
    public ResponseEntity<ResultDto<ResponsePostDto>> resisterPost(@RequestBody ResisterPostDto request) {

        ResisterPostCommand resisterPostCommand = new ResisterPostCommand(
                request.getEmail(),
                request.getCategoryId(),
                request.getTitle(),
                request.getPostContent(),
                request.getPostDate()
        );

        Post postDomain = resisterPostUseCase.registerPost(resisterPostCommand);

        ResponsePostDto responseDto = ResponseMapper.mapToPostDomain(postDomain);

        ResultDto<ResponsePostDto> result = new ResultDto<>(200, "게시물 저장 완료", responseDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
