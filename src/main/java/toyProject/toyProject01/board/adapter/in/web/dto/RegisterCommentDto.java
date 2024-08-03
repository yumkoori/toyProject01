package toyProject.toyProject01.board.adapter.in.web.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterCommentDto {

    private final Long postId;
    private final Long memberNo;
    private final String content;
    @Nullable
    private Long parentId;

}
