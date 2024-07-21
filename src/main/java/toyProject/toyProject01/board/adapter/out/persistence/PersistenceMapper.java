package toyProject.toyProject01.board.adapter.out.persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CategoryJpaEntity;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;
import toyProject.toyProject01.member.adapter.out.persistence.MemberMapper;

@Component
@RequiredArgsConstructor
@Getter
public class PersistenceMapper {

    //Domain -> JpaEntity
    PostJpaEntity mapToPostJpaEntity(Post post, MemberJpaEntity memberJpaEntity) {

        return new PostJpaEntity(
                post.getPostId(),
                memberJpaEntity,
                mapToCategoryJpaEntity(post.getCategory()),
                post.getTitle(),
                post.getPostContent(),
                post.getPostDate()
        );
    }

    //Domain -> JpaEntity
    CategoryJpaEntity mapToCategoryJpaEntity(Category category) {
        return new CategoryJpaEntity(
                category.getCategoryId(),
                category.getType()
        );
    }

    //JpaEntity -> Domain
    Post mapToPostDomain(PostJpaEntity postJpaEntity) {
        return new Post(
                postJpaEntity.getPostId(),
                postJpaEntity.getMember().getNo(),
                mapToCategoryDomain(postJpaEntity.getCategory()),
                postJpaEntity.getTitle(),
                postJpaEntity.getPostContent(),
                postJpaEntity.getPostDate()
        );
    }

    //JpaEntity -> Domain
    Category mapToCategoryDomain(CategoryJpaEntity categoryJpaEntity) {
        return new Category(
                categoryJpaEntity.getCategoryId(),
                categoryJpaEntity.getType()
        );
    }

}
