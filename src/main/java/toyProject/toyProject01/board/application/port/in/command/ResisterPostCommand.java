package toyProject.toyProject01.board.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import toyProject.toyProject01.common.SelfValidating;

import java.util.Date;

@Getter
public class ResisterPostCommand extends SelfValidating<ResisterPostCommand> {

    @NotNull(message = "회원 정보는 필수입니다.")
    private final String email;

    //@NotNull(message = "카테고리 지정은 필수입니다.")
    private final Long category;

    @NotBlank
    @Size(min = 1, max = 10, message = "제목은 최소 1글자, 최대 10글자를 입력하세요.")
    private final String title;

    @NotBlank
    @Size(min = 1, max = 50, message = "내용은 최소 1글자, 최대 50글자를 입력하세요.")
    private final String postContent;

    @NotNull
    private final Date postDate;

    public ResisterPostCommand(String email, Long category, String title, String postContent, Date postDate) {
        this.email = email;
        this.category = category;
        this.title = title;
        this.postContent = postContent;
        this.postDate = postDate;

        this.validateSelf();
    }


}
