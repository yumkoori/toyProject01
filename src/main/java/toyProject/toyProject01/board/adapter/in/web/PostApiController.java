package toyProject.toyProject01.board.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import toyProject.toyProject01.board.adapter.in.web.dto.ResisterPostDto;
import toyProject.toyProject01.board.adapter.in.web.dto.ResponsePostDto;
import toyProject.toyProject01.board.adapter.in.web.dto.UpdatePostDto;
import toyProject.toyProject01.board.application.port.in.DeletePostUseCase;
import toyProject.toyProject01.board.application.port.in.LoadPostUseCase;
import toyProject.toyProject01.board.application.port.in.ResisterPostUseCase;
import toyProject.toyProject01.board.application.port.in.UpdatePostUseCase;
import toyProject.toyProject01.board.application.port.in.command.ResisterPostCommand;
import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
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
    private final UpdatePostUseCase updatePostUseCase;
    private final DeletePostUseCase deletePostUseCase;

    @PostMapping(value = "/posts/{memberNo}")
    public ResponseEntity<ResultDto<ResponsePostDto>> resisterPost(@PathVariable Long memberNo,
                                                                   @RequestBody ResisterPostDto request) {

        ResisterPostCommand resisterPostCommand = new ResisterPostCommand(
                memberNo,
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

    @GetMapping("/posts/{postId}")
    public ResponseEntity<ResultDto<ResponsePostDto>> findPostOne(@PathVariable Long postId) {

        //게시글 하나 조회
        Post findPost = loadPostUseCase.findPostOne(postId);
        //도메인을 응답Dto 객체로 변환
        ResponsePostDto responseDto = ResponseMapper.mapToPostDomain(findPost);
        //응답 결과 객체 생성
        ResultDto<ResponsePostDto> result = new ResultDto<>(200, "해당 게시물 조회 완료", responseDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<ResultDto<ResponsePostDto>> updatePost(
            @PathVariable Long postId,
            @RequestBody UpdatePostDto request){

        //update입력 모델 객체로 변환
        UpdatePostCommand updatePostCommand = new UpdatePostCommand(
                request.getCategoryId(),
                request.getTitle(),
                request.getPostContent()
        );

        //update 실행
        Post updatePost = updatePostUseCase.updatePost(postId, updatePostCommand);
        //응답 Dto로 변환
        ResponsePostDto responseDto = ResponseMapper.mapToPostDomain(updatePost);
        //응답 결과 객체 생성
        ResultDto<ResponsePostDto> result = new ResultDto<>(200, "게시물 수정 완료", responseDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ResultDto<Long>> deletePost(@PathVariable Long postId) {

        //게시글 삭제
        deletePostUseCase.deletePost(postId);

        //응답 결과 객체 생성
        ResultDto<Long> result = new ResultDto<>(200, "해당 게시물 조회 완료", postId);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
