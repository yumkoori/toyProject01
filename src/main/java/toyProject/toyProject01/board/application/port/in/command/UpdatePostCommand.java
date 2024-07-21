package toyProject.toyProject01.board.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.common.SelfValidating;
import java.util.Date;

@Getter
public class UpdatePostCommand extends SelfValidating<UpdatePostCommand> {

    //@NotNull(message = "카테고리 지정은 필수입니다.")
    private final Long categoryId;

    @NotBlank
    @Size(min = 1, max = 10, message = "제목은 최소 1글자, 최대 10글자를 입력하세요.")
    private final String title;

    @NotBlank
    @Size(min = 1, max = 50, message = "내용은 최소 1글자, 최대 50글자를 입력하세요.")
    private final String postContent;


    public UpdatePostCommand(Long categoryId, String title, String postContent) {
        this.categoryId = categoryId;
        this.title = title;
        this.postContent = postContent;

        this.validateSelf();
    }
}
