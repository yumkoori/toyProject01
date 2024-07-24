package toyProject.toyProject01.board.application.port.out;

import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.domain.Post;

import java.util.List;

public interface LoadPostPort {

    List<Post> findPostAll(int currentPage, int size, String sortType);

    Post findPostId(Long postId);
}
