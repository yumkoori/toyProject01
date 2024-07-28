package toyProject.toyProject01.board.application.port.in;

import toyProject.toyProject01.board.application.port.in.command.EditCommentCommand;

public interface EditCommentUseCase {

    void editComment(EditCommentCommand editCommand);
}
