package toyProject.toyProject01.board.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ResisterPostDto {

    private final Long categoryId;
    private final String title;
    private final String postContent;

}
