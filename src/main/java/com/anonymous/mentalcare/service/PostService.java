package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.*;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.ReadingPost;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.repository.ReadingPostRepository;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ReadingPostRepository readingPostRepository;

    public RandomPostResponseDto getRandomPost(User user) {
        /*
         *  랜덤한 게시글 받아오기
         *  1. 내가 넘기기 하지 않은 글이면서
         *  2. 내가 작성하지 않은 글이 출력되어야 한다.
         *
         *  문제점 : findAll 하면 전체 목록을 받아오기때문에 리소스 낭비가 심하다.
         *  해결방법 : 페이징 기법으로 내용을 게시글이 1개인 모든 페이지로 만들고 랜덤인덱스로 가져왔다.
         *
         *  문제점2 : 이미 읽은 글 목록을 어떻게 받아와야 할 지 모르겠다. for문 돌리면 되긴 하는데, 그러면 페이징을 한 보람이 없다.
         *  해결방법 : findAllByUserNotAndPostIdNotIn 사용해서 풀어냈다. 코드가 너무 지저분해졌다.
         *
         *  문제점3 : NotIn 연산자에 들어갈 때 들어갈 readingPostList의 size가 0이면 레코드가 출력이 안 된다!!@!!!
         *  해결방법 : 당장 미봉책으로 size가 0일 땐 일반 검색 하는 식으로 했는데, 멘토님 조언으로 QueryDSL로 변경 예정.
         */

        List<ReadingPost> readingPostList = readingPostRepository.findAllByUser(user);
        List<Long> readingPostIdList = new ArrayList<>();
        for(ReadingPost readingPost : readingPostList){
            readingPostIdList.add(readingPost.getPost().getPostId());
        }
        System.out.println(readingPostIdList.toString());

        long qty;
        int idx;
        Page<Post> postPage = null;
        if(readingPostIdList.size() == 0){
            qty = postRepository.countByUserNot(user);
            idx = (int)(Math.random() * qty);
            postPage = postRepository
                    .findAllByUserNot(
                            user,
                            PageRequest.of(idx, 1)
                    );
        }else{
            qty = postRepository.countByUserNotAndPostIdNotIn(user, readingPostIdList);
            idx = (int)(Math.random() * qty);
            postPage = postRepository
                    .findAllByUserNotAndPostIdNotIn(
                            user,
                            readingPostIdList,
                            PageRequest.of(idx, 1)
                    );
        }
        System.out.println("record count : " + qty);

        RandomPostResponseDto randomPostResponseDto = null;
        if (postPage.hasContent()) {
            // 볼 수 있는 포스트가 있는 경우 postResponseDto 채워주고 반환.
            // 포스트가 더이상 없을 경우 null 반환.
            Post post = postPage.getContent().get(0);
            System.out.println("title : " + post.getTitle());
            System.out.println("content : " + post.getContent());

            List<RandomPostCommentResponseDto> commentList = new ArrayList<>();
            for(Comment comment:post.getCommentList()){
                commentList.add(new RandomPostCommentResponseDto(comment));
            }
            randomPostResponseDto = new RandomPostResponseDto(post, commentList);
            readingPostRepository.save(new ReadingPost(user, post));
        }

        return randomPostResponseDto;
    }

    public MyPostResponseDto getMyPost(Long postId, User user){
        Optional<Post> post = postRepository.findByPostIdAndUser(postId, user);
        if(!post.isPresent()){
            throw new NullPointerException("당신의 글이 아니거나 없는 글입니다!");
        }

        List<RandomPostCommentResponseDto> commentList = new ArrayList<>();
        for(Comment comment:post.get().getCommentList()){
            commentList.add(new RandomPostCommentResponseDto(comment));
        }

        return new MyPostResponseDto(post.get(), commentList);
    }

    public Post savePostService(PostDto.PostWrittenRequestDto postWrittenRequestDto, UserDetailsImpl userDetails){
        Post post = new Post(postWrittenRequestDto,userDetails.getUser());
        postRepository.save(post);
        return post;
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

    @Transactional
    public String updatePostService(Long postId, PostDto.PostUpdateRequestDto postUpdateRequestDto){
        Post findByIdPost = postRepository.findByPostId(postId).orElseThrow(()-> new IllegalArgumentException("찾는 포스트가 없습니다."));
        findByIdPost.updatePost(postUpdateRequestDto);
        return findByIdPost.getContent();
    }
}
