package toyProject.toyProject01.board.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;
import toyProject.toyProject01.member.domain.Member;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PostJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_Id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no")
    private MemberJpaEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity category;

    private String title;

    private String postContent;

    @CreatedDate
    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    @Transient
    private PostJpaEntity previousState;

    public PostJpaEntity(Long postId,
                         MemberJpaEntity member,
                         String title,
                         CategoryJpaEntity category,
                         String postContent,
                         LocalDateTime updateDateTime
    ) {
        this.postId = postId;
        this.member = member;
        this.title = title;
        this.category = category;
        this.postContent = postContent;
        this.updateDateTime = updateDateTime;
    }



    @PostLoad
    public void setPreviousState() {
        previousState = new PostJpaEntity();
        // copy fields
    }

    public void update(CategoryJpaEntity category, String title, String postContent) {
        this.category = category;
        this.title = title;
        this.postContent = postContent;
    }

    @PreUpdate
    public void preUpdate() {
        if (!title.equals(previousState.title) || !postContent.equals(previousState.postContent)) {
            updateDateTime = LocalDateTime.now();
        }
    }


}
