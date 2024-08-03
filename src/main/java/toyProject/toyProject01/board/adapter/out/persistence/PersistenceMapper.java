package toyProject.toyProject01.board.adapter.out.persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CategoryJpaEntity;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CommentEntity;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.board.domain.Comment;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;

@Component
@RequiredArgsConstructor
@Getter
public class PersistenceMapper {

    //Domain -> JpaEntity
    PostJpaEntity mapToPostJpaEntity(Post post, MemberJpaEntity memberJpaEntity) {

        return new PostJpaEntity(
                post.getPostId(),
                memberJpaEntity,
                post.getTitle(),
                mapToCategoryJpaEntity(post.getCategory()),
                post.getPostContent(),
                post.getCreateDateTime()
        );
    }

    //Domain -> JpaEntity
    CategoryJpaEntity mapToCategoryJpaEntity(Category category) {
        return new CategoryJpaEntity(
                category.getCategoryId()
        );
    }

    //JpaEntity -> Domain
    Post mapToPostDomain(PostJpaEntity postJpaEntity) {



        return new Post(
                postJpaEntity.getPostId(),
                new Post.PostMember(postJpaEntity.getMember().getNo(), postJpaEntity.getMember().getNickName()),
                mapToCategoryDomain(postJpaEntity.getCategory()),
                postJpaEntity.getTitle(),
                postJpaEntity.getPostContent(),
                postJpaEntity.getCreateDateTime(),
                postJpaEntity.getUpdateDateTime()
        );
    }

    //JpaEntity -> Domain
    Post mapToListPostDomain(PostJpaEntity postJpaEntity) {
        return new Post(
                postJpaEntity.getPostId(),
                new Post.PostMember(postJpaEntity.getMember().getNo(), postJpaEntity.getMember().getNickName()),
                mapToCategoryDomain(postJpaEntity.getCategory()),
                postJpaEntity.getTitle(),
                postJpaEntity.getPostContent(),
                postJpaEntity.getCreateDateTime()
        );
    }

    //JpaEntity -> Domain
    Category mapToCategoryDomain(CategoryJpaEntity categoryJpaEntity) {
        return new Category(
                categoryJpaEntity.getCategoryId(),
                categoryJpaEntity.getType()
        );
    }

    public static CommentEntity mapToCommentEntityForParent(Comment comment) {
        return new CommentEntity(
                comment.getPostId(),
                new MemberJpaEntity(comment.getMember().getMemberNo()),
                comment.getContent()
        );
    }

    public static CommentEntity mapToCommentEntityForReplies(Comment comment, Long parentId) {
        return new CommentEntity(
                comment.getPostId(),
                new MemberJpaEntity(comment.getMember().getMemberNo()),
                comment.getContent(),
                parentId
        );
    }

    public static Comment mapToCommentDomainForGetComments(CommentEntity commentEntity) {
        Long parentId = (commentEntity.getParent() != null) ? commentEntity.getParent().getCommentId() : null;

        if (commentEntity.isDeletedParent(commentEntity.getState())) {
            return Comment.mapToDeletedParentComment(commentEntity.getCommentId());
        } else {
            return Comment.mapToCommentForGetComments(
                    commentEntity.getCommentId(),
                    commentEntity.getMember().getNickName(),
                    commentEntity.getContent(),
                    commentEntity.getCreateTime(),
                    parentId
            );
        }
    }
}
