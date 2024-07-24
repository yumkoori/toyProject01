package toyProject.toyProject01.board.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import toyProject.toyProject01.board.adapter.out.persistence.entity.CategoryJpaEntity;
import toyProject.toyProject01.board.adapter.out.persistence.entity.PostJpaEntity;
import toyProject.toyProject01.board.application.port.in.command.UpdatePostCommand;
import toyProject.toyProject01.board.application.port.out.DeletePostPort;
import toyProject.toyProject01.board.application.port.out.LoadPostPort;
import toyProject.toyProject01.board.application.port.out.SavePostPort;
import toyProject.toyProject01.board.application.port.out.UpdatePostPort;
import toyProject.toyProject01.board.domain.Category;
import toyProject.toyProject01.board.domain.Post;
import toyProject.toyProject01.common.PersistenceAdapter;
import toyProject.toyProject01.member.adapter.out.persistence.MemberJpaEntity;
import toyProject.toyProject01.member.adapter.out.persistence.SpringDataMemberRepository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class PostPersistenceAdapter implements SavePostPort, LoadPostPort, UpdatePostPort, DeletePostPort {

    private final SpringDataPostRepository postRepository;
    private final PersistenceMapper persistenceMapper;

    @Override
    public Post savePost(Post post) {

        MemberJpaEntity memberJpaEntity = new MemberJpaEntity(post.getMember().getMemberNo());

        PostJpaEntity postJpaEntity = persistenceMapper.mapToPostJpaEntity(post , memberJpaEntity);

        PostJpaEntity savedPost = postRepository.save(postJpaEntity);

        return persistenceMapper.mapToPostDomain(savedPost);
    }

    //모든 게시판 데이터 조회후 반환
    @Override
    public List<Post> findPostAll(int currentPage, int size, String sortType) {

        PageRequest pageRequest =
                PageRequest.of(
                currentPage,
                size,
                Sort.by(Sort.Direction.ASC, sortType)
        );

         return postRepository.findAll(pageRequest).stream()
                 .map(persistenceMapper::mapToListPostDomain)
                 .collect(Collectors.toList());
    }

    @Override
    public Post findPostId(Long postId) {
        PostJpaEntity findPost = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Not Found Post: " + postId));

        return persistenceMapper.mapToPostDomain(findPost);
    }

    @Override
    public Post updatePost(Long targetId, Post updatePost) {

        //영속성 관리 객체
        PostJpaEntity findPostEntity = postRepository.findById(targetId)
                .orElseThrow(() -> new NoSuchElementException("Not Found Post: " + targetId));

        /*
        * 임시 카테고리 엔티티 생성, 추후에 수정 예정
        * */
        CategoryJpaEntity categoryEntity = new CategoryJpaEntity(1L, "도서");

        /*
        * 영속성 관리 객체이기 때문에, 객체의 필드 값이 변화 되면 자동으로 DB에 update 쿼리 반영
        * */
        findPostEntity.update(
                categoryEntity,
                updatePost.getTitle(),
                updatePost.getPostContent()
        );

        //도메인으로 변환
        return persistenceMapper.mapToPostDomain(findPostEntity);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
