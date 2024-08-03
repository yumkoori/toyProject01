package toyProject.toyProject01.board.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import toyProject.toyProject01.board.domain.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseCommentsDto {

    private final Long commentId;
    private final String nickName;
    private final String content;
    private final LocalDateTime createTime;
    private final List<ResponseReplyDto> replies;

    public ResponseCommentsDto(Long commentId, String nickName, String content, LocalDateTime createTime, List<ResponseReplyDto> replies) {
        this.commentId = commentId;
        this.nickName = nickName;
        this.content = content;
        this.createTime = createTime;
        this.replies = replies;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponseReplyDto {
        private final Long commentId;
        private final Long parentId;
        private final String nickName;
        private final String content;
        private final LocalDateTime createTime;
    }
}
