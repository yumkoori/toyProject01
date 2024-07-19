package toyProject.toyProject01.board.application.port.in;

import toyProject.toyProject01.board.application.port.in.command.ResisterPostCommand;
import toyProject.toyProject01.board.domain.Post;

public interface ResisterPostUseCase {

    Post registerPost(ResisterPostCommand resisterPostCommand);
}
