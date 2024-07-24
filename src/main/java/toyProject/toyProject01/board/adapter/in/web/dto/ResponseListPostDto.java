package toyProject.toyProject01.board.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResponseListPostDto {

    private final Long postID;
    private final String categoryType;
    private final String title;
    private final String nickName;
    private final LocalDateTime createDateTime;
}
