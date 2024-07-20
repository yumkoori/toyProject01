package toyProject.toyProject01.board.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.board.application.port.in.LoadPostUseCase;
import toyProject.toyProject01.board.application.port.in.ResisterPostUseCase;
import toyProject.toyProject01.board.application.port.in.UpdatePostUseCase;
import toyProject.toyProject01.board.application.port.in.command.ResisterPostCommand;
import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.application.port.out.LoadPostPort;
import toyProject.toyProject01.board.application.port.out.SavePostPort;
import toyProject.toyProject01.board.application.port.out.UpdatePostPort;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.UseCase;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.domain.Member;

import java.util.List;

@UseCase
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ResisterPostService implements ResisterPostUseCase, LoadPostUseCase, UpdatePostUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SavePostPort savePostPort;
    private final LoadPostPort loadPostPort;
    private final UpdatePostPort updatePostPort;

    @Override
    public Post registerPost(ResisterPostCommand resisterPostCommand) {

        Member member = loadMemberPort.loadMemberWithEmail(resisterPostCommand.getEmail());

        //임시 카테고리 생성
        //추후에 입력 모델에서 넘어온 카테고리Id를 통해서 카테고리 객체를 가져와서 매핑하도록 할 예정
        Category category = new Category(1L, "도서");

        Post postDomain = new Post(
                null,
                member,
                category,
                resisterPostCommand.getTitle(),
                resisterPostCommand.getPostContent(),
                resisterPostCommand.getPostDate()
        );

        savePostPort.savePost(postDomain);
        return postDomain;
    }

    @Override
    public List<Post> findPostAll() {
        return loadPostPort.findPostAll();
    }

    @Override
    public Post findPostOne(Long postId) {

        return loadPostPort.findPostId(postId);
    }

    @Override
    @Transactional
    public Post updatePost(Long postId, UpdatePostCommand updatePostCommand) {

        //임시 카테고리 객체, 나중에 수정 예정
        Category category = new Category(1L, "도서");

        //Post 도메인으로 변환
        Post updateDomain = new Post(
                null,
                null,
                category,
                updatePostCommand.getTitle(),
                updatePostCommand.getPostContent(),
                null
        );

        return updatePostPort.updatePost(postId, updateDomain);

    }
}
