package toyProject.toyProject01.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Value
public class Comment {

    private Long commentId;
    private Long postId;
    private CommentMember member;
    private String content;
    private LocalDateTime createTime;
    private Long parentId;
    private List<Comment> replies;

    @Getter
    @AllArgsConstructor
    public static class CommentMember {
        private final Long memberNo;
        private final String nickName;
    }

    public static Comment mapToCommentForRegister(Long postId, Long memberNo, String content, Long parentId) {
        return new Comment(null, postId, new CommentMember(memberNo, null), content, null, parentId, null);
    }

    public static Comment mapToCommentForGetComments (
            Long commentId,
            String nickName,
            String content,
            LocalDateTime createTime,
            Long parentId
    ) {
        return new Comment(commentId, null, new CommentMember(null, nickName), content, createTime, parentId, new ArrayList<>());
    }

    public static Comment mapToCommentForEdit(Long commentId, String content) {
        return new Comment(commentId, null, new CommentMember(null,null), content, null, null, null);
    }

    public static Comment mapToDeletedParentComment(Long commentId) {
        return new Comment(commentId, null, new CommentMember(null, null), "삭제된 내용입니다.", null, null, new ArrayList<>());
    }
}
