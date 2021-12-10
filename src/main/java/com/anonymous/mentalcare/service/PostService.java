package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.post.PostDto;
import com.anonymous.mentalcare.dto.post.RandomPostResponseDto;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.QPost;
import com.anonymous.mentalcare.models.ReadingPost;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.repository.ReadingPostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.util.ValidateChecker;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ReadingPostRepository readingPostRepository;
    private final EntityManager entityManager;

    public RandomPostResponseDto getRandomPost(UserDetailsImpl userDetails) {
        /*
         *  랜덤한 게시글 받아오기
         *  1. 내가 넘기기 하지 않은 글이면서
         *  2. 내가 작성하지 않은 글이 출력되어야 한다.
         *
         *  문제점 : findAll 하면 전체 목록을 받아오기때문에 리소스 낭비가 심하다.
         *  해결방법 : 페이징 기법으로 내용을 게시글이 1개인 모든 페이지로 만들고 랜덤인덱스로 가져왔다.
         *
         *  문제점2 : 이미 읽은 글 목록을 어떻게 받아와야 할 지 모르겠다. for 문 돌리면 되긴 하는데, 그러면 페이징을 한 보람이 없다.
         *  해결방법 : findAllByUserNotAndPostIdNotIn 사용해서 풀어냈다. 코드가 너무 지저분해졌다.
         *
         *  문제점3 : NotIn 연산자에 들어갈 때 들어갈 readingPostList 의 size 가 0이면 레코드가 출력이 안 된다!!@!!!
         *  해결방법 : 당장 미봉책으로 size 가 0일 땐 일반 검색 하는 식으로 했는데, 멘토님 조언으로 QueryDSL 로 변경 예정.
         */

        User user = ValidateChecker.userDetailsIsNull(userDetails);

        List<ReadingPost> readingPostList = readingPostRepository.findAllByUser(user);
        List<Long> readingPostIdList = new ArrayList<>();
        for (ReadingPost readingPost : readingPostList) {
            readingPostIdList.add(readingPost.getPost().getPostId());
        }
        System.out.println("이미 읽은 글의 Id : " + readingPostIdList);

        Post randomPost = randomPostPick(user, readingPostIdList);

        RandomPostResponseDto randomPostResponseDto = null;
        if(randomPost != null){
            readingPostRepository.save(new ReadingPost(user, randomPost));
            randomPostResponseDto = new RandomPostResponseDto(randomPost);
            System.out.println("title : " + randomPost.getTitle());
            System.out.println("content : " + randomPost.getContent());
        }else{
            System.out.println("더 이상 읽을 수 있는 글이 없습니다!");
        }

        return randomPostResponseDto;
    }

    private Post randomPostPick(User user, List<Long> readingPostIdList) {
        JPAQuery<Post> query = new JPAQuery<>(entityManager);
        QPost qPost = new QPost("p");

        // readingPostIdList 가 비어있으면 내가 작성하지 않은 글 조회,
        // 비어있지 않으면 내가 작성하지 않았으면서 readingPostIdLIst 에 없는 글 조회(내가 읽지 않은 글)
        boolean readingPostIdListIsNull = readingPostIdList.size() == 0;

        long cnt = readingPostIdListIsNull ?
                postRepository.countByUserNot(user) :
                postRepository.countByUserNotAndPostIdNotIn(user, readingPostIdList);
        long index = (long) (Math.random() * cnt);
        System.out.println("남은 게시글 수 : " + cnt);

        QueryResults<Post> queryResults = query.from(qPost)
                .where(qPost.user.ne(user)
                        .and(readingPostIdListIsNull ?
                                qPost.user.ne(user) :
                                qPost.postId.notIn(readingPostIdList))
                ).offset(index)
                .limit(1)
                .fetchResults();

        if(queryResults.isEmpty()){
            return null;
        }else {
            return queryResults.getResults().get(0);
        }
    }

    public void savePostService(PostDto.PostWrittenRequestDto postWrittenRequestDto, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        if (postWrittenRequestDto.getTitle().trim().equals("")) {
            throw new IllegalArgumentException("제목을 입력해주세요.");
        } else if (postWrittenRequestDto.getContent().trim().equals("")) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }

        Post post = new Post(postWrittenRequestDto, user);
        postRepository.save(post);
    }

    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        User user = ValidateChecker.userDetailsIsNull(userDetails);

        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new NullPointerException("유효하지 않거나 이미 삭제된 글입니다.");
        }
        if (!user.getId().equals(post.get().getUser().getId())) {
            throw new IllegalArgumentException("당신의 게시글이 아닙니다.");
        }

        postRepository.deleteById(postId);
    }

    @Transactional
    public String updatePostService(Long postId, PostDto.PostUpdateRequestDto postUpdateRequestDto) {
        Post findByIdPost = postRepository.findByPostId(postId).orElseThrow(() -> new IllegalArgumentException("찾는 포스트가 없습니다."));
        findByIdPost.updatePost(postUpdateRequestDto);
        return findByIdPost.getContent();
    }
}
