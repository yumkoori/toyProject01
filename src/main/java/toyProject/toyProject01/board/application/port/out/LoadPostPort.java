package toyProject.toyProject01.board.application.port.out;

import toyProject.toyProject01.board.domain.Post;

import java.util.List;

public interface LoadPostPort {

    List<Post> findPostAll();
}
