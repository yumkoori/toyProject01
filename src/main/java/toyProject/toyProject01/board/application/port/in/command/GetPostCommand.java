package toyProject.toyProject01.board.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import toyProject.toyProject01.common.SelfValidating;

@Getter
public class GetPostCommand extends SelfValidating<ResisterPostCommand> {

    @NotNull
    private final int currentPage;

    @NotNull
    private final int size;

    @NotBlank
    private final String sortType;

    public GetPostCommand(int currentPage, int size, String sortType) {
        this.currentPage = currentPage;
        this.size = size;
        this.sortType = sortType;

        this.validateSelf();
    }
}
