package toyProject.toyProject01.board.adapter.in.web;

import org.springframework.stereotype.Component;
import toyProject.toyProject01.board.adapter.in.web.dto.ResponseListPostDto;
import toyProject.toyProject01.board.adapter.in.web.dto.ResponsePostDto;
import toyProject.toyProject01.board.domain.Post;

@Component
public class ResponseMapper {

    static ResponsePostDto mapToPostDomain(Post post) {
        return new ResponsePostDto (
                post.getMember().getMemberNo(),
                post.getCategory().getType(),
                post.getTitle(),
                post.getPostContent(),
                post.getCreateDateTime()
        );
    }

    static ResponseListPostDto mapToListPostDto(Post post) {
        return new ResponseListPostDto(
                post.getPostId(),
                post.getCategory().getType(),
                post.getTitle(),
                post.getMember().getNickName(),
                post.getCreateDateTime()
        );
    }
}
