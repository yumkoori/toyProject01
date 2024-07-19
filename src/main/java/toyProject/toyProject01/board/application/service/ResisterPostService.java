package toyProject.toyProject01.board.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.board.application.port.in.ResisterPostUseCase;
import toyProject.toyProject01.board.application.port.in.command.ResisterPostCommand;
import toyProject.toyProject01.board.application.port.out.SavePostPort;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.UseCase;
import toyProject.toyProject01.member.application.port.out.LoadMemberPort;
import toyProject.toyProject01.member.domain.Member;

@UseCase
@Service
@Transactional
@AllArgsConstructor
public class ResisterPostService implements ResisterPostUseCase {

    private final SavePostPort savePostPort;
    private final LoadMemberPort loadMemberPort;

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
}
