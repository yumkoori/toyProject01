package toyProject.toyProject01.board.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.board.application.port.in.RegisterCommentUseCase;
import toyProject.toyProject01.board.application.port.in.command.RegisterCommentCommand;
import toyProject.toyProject01.board.application.port.out.RegisterCommentPort;
import toyProject.toyProject01.board.domain.Comment;
import toyProject.toyProject01.common.UseCase;

@UseCase
@Transactional
@AllArgsConstructor
@Slf4j
public class CommentService implements RegisterCommentUseCase {

    private final RegisterCommentPort registerPort;

    @Override
    public void registerComment(RegisterCommentCommand registerCommand) {
        Comment comment = Comment.mapToCommentForRegister(
                registerCommand.getPostId(),
                registerCommand.getNickName(),
                registerCommand.getContent(),
                registerCommand.getParentId()
        );

        if(comment.getParentId() == null) {
            registerPort.saveParentComment(comment);
        } else {
            registerPort.saveRepliesComment(comment);
        }
    }

}

