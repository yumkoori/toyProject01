package toyProject.toyProject01.board.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.board.application.port.in.LoadCommentUseCase;
import toyProject.toyProject01.board.application.port.in.RegisterCommentUseCase;
import toyProject.toyProject01.board.application.port.in.command.RegisterCommentCommand;
import toyProject.toyProject01.board.application.port.out.LoadCommentsPort;
import toyProject.toyProject01.board.application.port.out.RegisterCommentPort;
import toyProject.toyProject01.board.domain.Comment;
import toyProject.toyProject01.common.UseCase;

import java.util.ArrayList;
import java.util.List;

@UseCase
@Transactional
@AllArgsConstructor
@Slf4j
public class CommentService implements RegisterCommentUseCase, LoadCommentUseCase {

    private final RegisterCommentPort registerPort;
    private final LoadCommentsPort loadPort;

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

    @Override
    public List<Comment> getComments(Long postId) {
        List<Comment> getComments = loadPort.getComments(postId);

        return sortComments(getComments);
    }

    private List<Comment> sortComments(List<Comment> comments) {
        List<Comment> resultComments = new ArrayList<>();
        //부모-자식 댓글 계층 정리
        for (Comment comment : comments) {
            if (comment.getParentId() == null) {
                resultComments.add(comment);
            } else {
                for (Comment parentComment : resultComments) {
                    if (comment.getParentId().equals(parentComment.getCommentId())) {
                        parentComment.getReplies().add(comment);
                        break;
                    }
                }
            }
        }

        return resultComments;
    }
}

