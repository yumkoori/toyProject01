package toyProject.toyProject01.common;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultDto<T> {

    private final Integer resultCode;
    private final String message;
    private final T data;

}
