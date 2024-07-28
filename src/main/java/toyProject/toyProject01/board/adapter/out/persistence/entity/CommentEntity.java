package toyProject.toyProject01.board.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "comment")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long commentId;

    @Column(name = "postId")
    private Long postId;

    @Column(name = "nickName")
    private String memberNickName;

    @Column(name = "content")
    private String content;

    @CreatedDate
    @Column(name = "createTime")
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    private CommentState state = CommentState.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CommentEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<CommentEntity> replies = new ArrayList<>();

    public enum CommentState {
        ACTIVE,
        DELETE,
        HIDE,
        REVIEW
    }

    public void updateToEditComment(String content) {
        this.content = content;
    }

    public CommentEntity(Long postId, String memberNickName, String content) {
        this.postId = postId;
        this.memberNickName = memberNickName;
        this.content = content;
    }

    public CommentEntity(Long postId, String memberNickName, String content, CommentEntity parent) {
        this.postId = postId;
        this.memberNickName = memberNickName;
        this.content = content;
        this.parent = parent;
    }
}
