package toyProject.toyProject01.board.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;
import toyProject.toyProject01.board.application.port.out.LoadPostPort;
import toyProject.toyProject01.board.application.port.out.SavePostPort;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.PersistenceAdapter;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostPersistenceAdapter implements SavePostPort, LoadPostPort {

    private final SpringDataPostRepository repository;
    private final PersistenceMapper persistenceMapper;

    @Override
    public void savePost(Post post) {

        PostJpaEntity postJpaEntity = persistenceMapper.mapToPostJpaEntity(post);

        repository.save(postJpaEntity);

    }

    //모든 게시판 데이터 조회후 반환
    @Override
    public List<Post> findPostAll() {
        List<PostJpaEntity> findAllPosts = repository.findAll();

        return findAllPosts.stream()
                .map(persistenceMapper::mapToPostDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Post findPostId(Long postId) {
        PostJpaEntity findPost = repository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Not Found Post: " + postId));

        return persistenceMapper.mapToPostDomain(findPost);
    }
}
