package toyProject.toyProject01.board.application.port.in.command;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import toyProject.toyProject01.common.SelfValidating;

@Getter
public class RegisterCommentCommand extends SelfValidating<RegisterCommentCommand> {

    @NotNull
    private final Long postId;
    @NotBlank
    private final String nickName;
    @NotBlank
    private final String content;
    @Nullable
    private final Long parentId;

    public RegisterCommentCommand(Long postId, String nickName, String content, @Nullable Long parentId) {
        this.postId = postId;
        this.nickName = nickName;
        this.content = content;
        this.parentId = parentId;

        this.validateSelf();
    }

}

