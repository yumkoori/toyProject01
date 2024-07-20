package toyProject.toyProject01.board.application.port.in;

import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.domain.Post;

import java.util.List;

public interface LoadPostUseCase {

    List<Post> findPostAll();

    Post findPostOne(Long postId);
}
