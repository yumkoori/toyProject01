package toyProject.toyProject01.board.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostDto {

    private final Long categoryId;
    private final String title;
    private final String postContent;
}
