package toyProject.toyProject01.board.domain;

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
    private String nickName;
    private String content;
    private LocalDateTime createTime;
    private Long parentId;
    private List<Comment> replies;

    public static Comment mapToCommentForRegister(Long postId, String nickName, String content, Long parentId) {
        return new Comment(null, postId, nickName, content, null, parentId, null);
    }

    public static Comment mapToCommentForGetComments (
            Long commentId,
            String nickName,
            String content,
            LocalDateTime createTime,
            Long parentId
    ) {
        return new Comment(commentId, null, nickName, content, createTime, parentId, new ArrayList<>());
    }

    public static Comment mapToCommentForEdit(Long commentId, String content) {
        return new Comment(commentId, null, null, content, null, null, null);
    }
}
