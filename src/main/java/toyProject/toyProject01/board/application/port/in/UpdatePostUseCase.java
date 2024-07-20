package toyProject.toyProject01.board.application.port.in;

import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.domain.Post;

public interface UpdatePostUseCase {

    Post updatePost(Long postId, UpdatePostCommand updatePostCommand);
}
