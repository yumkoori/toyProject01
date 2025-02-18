package toyProject.toyProject01.board.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import toyProject.toyProject01.common.SelfValidating;

@Getter
public class EditCommentCommand extends SelfValidating<EditCommentCommand> {

    @NotNull
    private final Long commentId;
    @NotBlank
    private final String content;
    @NotNull
    private final Long memberNo;

    public EditCommentCommand(Long commentId, String content, Long memberNo) {
        this.commentId = commentId;
        this.content = content;
        this.memberNo = memberNo;

        this.validateSelf();
    }
}
