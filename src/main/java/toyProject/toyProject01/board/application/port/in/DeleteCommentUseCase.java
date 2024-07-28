package toyProject.toyProject01.board.application.port.in;

public interface DeleteCommentUseCase {

    void softDeleteComment(Long commentId);
}
