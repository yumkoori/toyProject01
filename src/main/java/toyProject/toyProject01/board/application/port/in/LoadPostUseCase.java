package toyProject.toyProject01.board.application.port.in;

import toyProject.toyProject01.board.application.port.in.command.GetPostCommand;
import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.domain.Post;

import java.util.List;

public interface LoadPostUseCase {

    List<Post> getPostList(GetPostCommand getPostCommand);

    Post findPostOne(Long postId);
}
