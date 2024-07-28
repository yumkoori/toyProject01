package toyProject.toyProject01.board.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import toyProject.toyProject01.board.application.port.in.command.RegisterCommentCommand;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("부모 댓글 저장")
    void registerParentComment() {
        //given
        RegisterCommentCommand registerCommentCommand =
                new RegisterCommentCommand(17L, "댓글", "test1", null);

        //when
        commentService.registerComment(registerCommentCommand);

        //then

    }

    @Test
    @DisplayName("대댓글 저장")
    void registerRepliesComment() {
        //given
        RegisterCommentCommand registerCommentCommand =
                new RegisterCommentCommand(17L, "대댓글", "대댓글", 1L);

        //when
        commentService.registerComment(registerCommentCommand);

        //then

    }

}