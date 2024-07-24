package toyProject.toyProject01.board.application.port.out;

import toyProject.toyProject01.board.domain.Post;

public interface SavePostPort {

    Post savePost(Post post);
}
