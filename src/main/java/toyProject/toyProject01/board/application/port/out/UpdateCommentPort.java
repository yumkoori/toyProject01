package toyProject.toyProject01.board.application.port.out;

import toyProject.toyProject01.board.domain.Comment;

public interface UpdateCommentPort {

    void updateToEditComment(Comment editComment);
}
