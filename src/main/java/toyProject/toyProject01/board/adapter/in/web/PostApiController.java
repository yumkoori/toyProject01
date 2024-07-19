package toyProject.toyProject01.board.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyProject.toyProject01.board.adapter.in.web.dto.ResisterPostDto;
import toyProject.toyProject01.board.adapter.in.web.dto.ResponsePostDto;
import toyProject.toyProject01.board.application.port.in.LoadPostUseCase;
import toyProject.toyProject01.board.application.port.in.ResisterPostUseCase;
import toyProject.toyProject01.board.application.port.in.command.ResisterPostCommand;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.ResultDto;
import toyProject.toyProject01.common.WebAdapter;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final ResisterPostUseCase resisterPostUseCase;
    private final LoadPostUseCase loadPostUseCase;

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

    //모든 게시물 조회
    @GetMapping("/posts")
    public ResponseEntity<ResultDto<List<ResponsePostDto>>> findAllPosts() {

        //모든 게시물 리스트로 조회
        //응답Dto타입의 리스트로 변환
        List<ResponsePostDto> responseDto = loadPostUseCase.findPostAll().stream()
                .map(ResponseMapper::mapToPostDomain)
                .collect(Collectors.toList());

        ResultDto<List<ResponsePostDto>> result = new ResultDto<>(200, "게시물 조회 완료", responseDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
