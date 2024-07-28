package toyProject.toyProject01.board.application.port.out;

import toyProject.toyProject01.board.domain.Comment;

import java.util.List;

public interface LoadCommentsPort {

    List<Comment> getComments(Long postId);
}
