package toyProject.toyProject01.board.adapter.in.web;

import org.springframework.stereotype.Component;
import toyProject.toyProject01.board.adapter.in.web.dto.ResponsePostDto;
import toyProject.toyProject01.board.domain.Post;

@Component
public class ResponseMapper {

    static ResponsePostDto mapToPostDomain(Post post) {
        return new ResponsePostDto (
                post.getMember().getEmail(),
                post.getCategory().getType(),
                post.getTitle(),
                post.getPostContent(),
                post.getPostDate()
        );
    }
}
