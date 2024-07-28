package toyProject.toyProject01.board.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import toyProject.toyProject01.board.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ResponseCommentsDto {

    private final Long commentId;
    private final Long parentId;
    private final String nickName;
    private final String content;
    private final LocalDateTime createTime;
    private final List<Comment> replies;

}
