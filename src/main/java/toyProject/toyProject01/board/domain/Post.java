package toyProject.toyProject01.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import toyProject.toyProject01.member.domain.Member;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Post {

    private final Long postId;
    private final Member member;
    private final Category category;
    private final String title;
    private final String postContent;
    private final Date postDate;

}
