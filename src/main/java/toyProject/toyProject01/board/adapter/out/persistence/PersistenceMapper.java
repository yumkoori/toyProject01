package toyProject.toyProject01.board.adapter.out.persistence;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CategoryJpaEntity;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.member.adapter.out.persistence.MemberMapper;

@Component
@RequiredArgsConstructor
@Getter
public class PersistenceMapper {

    PostJpaEntity mapToPostJpaEntity(Post post) {

        return new PostJpaEntity(
                post.getPostId(),
                MemberMapper.mapToJpaEntity(post.getMember()),
                mapToCategoryJpaEntity(post.getCategory()),
                post.getTitle(),
                post.getPostContent(),
                post.getPostDate());
    }

    CategoryJpaEntity mapToCategoryJpaEntity(Category category) {
        return new CategoryJpaEntity(
                category.getCategoryId(),
                category.getType()
        );
    }

}
