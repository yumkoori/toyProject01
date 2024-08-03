package toyProject.toyProject01.board.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;

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
    @Column(name = "commentId")
    private Long commentId;

    @Column(name = "postId")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private MemberJpaEntity member;

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
        DELETE_PARENT,
        HIDE,
        REVIEW
    }


    public void updateToEditComment(String content) {
        this.content = content;
    }

    public CommentEntity(Long postId, MemberJpaEntity member, String content) {
        this.postId = postId;
        this.member = member;
        this.content = content;
    }

    public CommentEntity(Long postId, MemberJpaEntity member, String content, Long parentId) {
        this.postId = postId;
        this.member = member;
        this.content = content;
        this.parent = new CommentEntity(parentId);
    }

    public CommentEntity(Long parentId) {
        this.commentId = parentId;
    }

     public boolean isDeletedParent(CommentState state) {
        return state.equals(CommentState.DELETE_PARENT);
    }

    public void updateToDelete_Parent_State() {
        this.state = CommentState.DELETE_PARENT;
    }

    public void updateToDelete_State() {
        this.state = CommentState.DELETE;
    }
}
