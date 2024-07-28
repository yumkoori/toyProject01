package toyProject.toyProject01.board.application.port.in;

import toyProject.toyProject01.board.application.port.in.command.RegisterCommentCommand;

public interface RegisterCommentUseCase {

    void registerComment(RegisterCommentCommand registerComment);

}
