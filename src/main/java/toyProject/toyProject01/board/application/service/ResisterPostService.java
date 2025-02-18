package toyProject.toyProject01.board.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.board.application.port.in.*;
import toyProject.toyProject01.board.application.port.in.command.GetPostCommand;
import toyProject.toyProject01.board.application.port.in.command.ResisterPostCommand;
import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.application.port.out.*;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.UseCase;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;

import java.util.List;

@UseCase
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ResisterPostService implements
        ResisterPostUseCase, LoadPostUseCase, UpdatePostUseCase , DeletePostUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SavePostPort savePostPort;
    private final LoadPostPort loadPostPort;
    private final UpdatePostPort updatePostPort;
    private final DeletePostPort deletePostPort;
    private final ChangePostStatePort changePostStatePort;

    @Override
    public Post registerPost(ResisterPostCommand resisterPostCommand) {

        //임시 카테고리 생성
        //추후에 입력 모델에서 넘어온 카테고리Id를 통해서 카테고리 객체를 가져와서 매핑하도록 할 예정
        Category category = new Category(1L, "도서");

        Post postDomain = new Post(
                null,
                new Post.PostMember(resisterPostCommand.getMemberNo(), null),
                category,
                resisterPostCommand.getTitle(),
                resisterPostCommand.getPostContent(),
                null,
                null
        );

        return savePostPort.savePost(postDomain);

    }

    @Override
    public List<Post> getPostList(GetPostCommand getPostCommand) {

        return loadPostPort.findPostAll(
                getPostCommand.getCurrentPage(),
                getPostCommand.getSize(),
                getPostCommand.getSortType()
        );
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
                null,
                null
        );

        return updatePostPort.updatePost(postId, updateDomain);

    }

    @Override
    public void deletePost(Long postId) {
        deletePostPort.deletePost(postId);
    }

    @Override
    public void softDeletePost(Long postId) {
        changePostStatePort.stateToDelete(postId);
    }

}
