package toyProject.toyProject01.board.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;
import toyProject.toyProject01.board.application.port.out.SavePostPort;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostPersistenceAdapter implements SavePostPort {

    private final SpringDataPostRepository repository;
    private final PersistenceMapper persistenceMapper;

    @Override
    public void savePost(Post post) {

        PostJpaEntity postJpaEntity = persistenceMapper.mapToPostJpaEntity(post);

        repository.save(postJpaEntity);

    }

}
