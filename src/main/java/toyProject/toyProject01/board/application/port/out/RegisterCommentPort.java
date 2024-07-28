package toyProject.toyProject01.board.application.port.out;

import toyProject.toyProject01.board.domain.Comment;

public interface RegisterCommentPort {

    void saveParentComment(Comment comment);

    void saveRepliesComment(Comment comment);
}
