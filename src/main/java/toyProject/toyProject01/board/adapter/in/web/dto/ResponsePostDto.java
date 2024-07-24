package toyProject.toyProject01.board.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toyProject.toyProject01.board.domain.Post;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ResponsePostDto {

    private final Long memberNo;
    private final String category;
    private final String title;
    private final String content;
    private final LocalDateTime createDateTime;

}
