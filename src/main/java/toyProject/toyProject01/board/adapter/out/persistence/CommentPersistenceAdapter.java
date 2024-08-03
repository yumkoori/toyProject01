package toyProject.toyProject01.board.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CommentEntity;
import toyProject.toyProject01.board.application.port.out.LoadCommentsPort;
import toyProject.toyProject01.board.application.port.out.RegisterCommentPort;
import toyProject.toyProject01.board.application.port.out.UpdateCommentPort;
import toyProject.toyProject01.board.domain.Comment;
import toyProject.toyProject01.common.PersistenceAdapter;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class CommentPersistenceAdapter implements RegisterCommentPort, LoadCommentsPort, UpdateCommentPort {

    private final SpringDataCommentRepository repository;

    @Override
    public void saveParentComment(Comment comment) {
        CommentEntity parentComment = PersistenceMapper.mapToCommentEntityForParent(comment);
        repository.save(parentComment);
    }

    @Override
    public void saveRepliesComment(Comment comment) {
        CommentEntity repliesEntity = PersistenceMapper.mapToCommentEntityForReplies(comment, comment.getParentId());
        repository.save(repliesEntity);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        return repository.getComments(postId, CommentEntity.CommentState.ACTIVE, CommentEntity.CommentState.DELETE_PARENT)
                .stream()
                .map(PersistenceMapper::mapToCommentDomainForGetComments)
                .collect(Collectors.toList());
    }

    @Override
    public void updateToEditComment(Comment editComment) {
        CommentEntity savedComment = repository.findById(editComment.getCommentId()).orElseThrow();

        savedComment.updateToEditComment(editComment.getContent());
    }

    @Override
    public void updateToDeleteState(Long commentId) {
        CommentEntity savedComment = repository.findById(commentId).orElseThrow();
        if (savedComment.getParent() == null) {
            savedComment.updateToDelete_Parent_State();
        } else {
            savedComment.updateToDelete_State();
        }
    }
}
