package toyProject.toyProject01.board.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestPageDto {

    private final int currentPage;
    private final int size;
    private final String sortType;

}
