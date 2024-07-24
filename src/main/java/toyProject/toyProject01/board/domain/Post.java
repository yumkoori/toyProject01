package toyProject.toyProject01.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import toyProject.toyProject01.member.domain.Member;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Post {

    private final Long postId;
    private final PostMember member;
    private final Category category;
    private final String title;
    private final String postContent;
    private final LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;


    @Getter
    @AllArgsConstructor
    public static class PostMember {
        private final Long memberNo;
        private final String nickName;
    }

    public Post(Long postId, PostMember member, Category category, String title, String postContent, LocalDateTime createDateTime) {
        this.postId = postId;
        this.member = member;
        this.category = category;
        this.title = title;
        this.postContent = postContent;
        this.createDateTime = createDateTime;
    }
}


