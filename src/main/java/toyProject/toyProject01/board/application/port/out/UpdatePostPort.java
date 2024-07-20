package toyProject.toyProject01.board.application.port.out;

import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.domain.Post;

public interface UpdatePostPort {

    Post updatePost(Long targetId, Post updatePost);
}
