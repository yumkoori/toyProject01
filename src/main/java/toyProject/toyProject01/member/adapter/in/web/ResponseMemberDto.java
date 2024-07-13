package toyProject.toyProject01.member.adapter.in.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

public class ResponseMemberDto {

    private final String nickName;

    static ResponseMemberDto mapToResponseDto(String nickName) {
        return new ResponseMemberDto(nickName);
    }
}
