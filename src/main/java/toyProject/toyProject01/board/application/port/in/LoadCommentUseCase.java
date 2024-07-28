package toyProject.toyProject01.board.application.port.in;

import toyProject.toyProject01.board.domain.Comment;
import java.util.List;

public interface LoadCommentUseCase {

    List<Comment> getComments(Long postId);
}
