package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.MyPostResponseDto;
import com.anonymous.mentalcare.dto.PostDto;
import com.anonymous.mentalcare.dto.PostResponseDto;
import com.anonymous.mentalcare.dto.RandomPostResponseDto;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.ReadingPost;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.PostRepository;
import com.anonymous.mentalcare.repository.ReadingPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ReadingPostRepository readingPostRepository;

    public RandomPostResponseDto getRandomPost(User user) {
        // 랜덤한 게시글 받아오기
        // findAll 하면 전체 목록을 받아오기때문에 리소스 낭비가 심하다.
        // 페이징 기법으로 내용을 게시글이 1개인 모든 페이지로 만들고 랜덤인덱스로 가져오면 된다.
        long qty = postRepository.count();
        int idx = (int)(Math.random() * qty);

        List<ReadingPost> readingPostList = readingPostRepository.findAllByUser(user);
        System.out.println("초반부");
        Page<Post> postPage = postRepository.findAll(PageRequest.of(idx, 1));
        postRepository.findAll(PageRequest.of(1,1));
        System.out.println("postPage 탐색");
        RandomPostResponseDto randomPostResponseDto = null;
        if (postPage.hasContent()) {
            System.out.println("postPage.hasContent");
            // 볼 수 있는 포스트가 있는 경우 postResponseDto 채워주고 반환.
            // 포스트가 더이상 없을 경우 null 반환.
            Post post = postPage.getContent().get(0);
            randomPostResponseDto = new RandomPostResponseDto(post);
            readingPostRepository.save(new ReadingPost(user, post));
        }
        System.out.println("postPage.hasn't Content");

        return randomPostResponseDto;
    }

    public MyPostResponseDto getMyPost(Long postId, User user){
        Optional<Post> post = postRepository.findByPostIdAndUser(postId, user);
        if(!post.isPresent()){
            throw new NullPointerException("당신의 글이 아니거나 없는 글입니다!");
        }

        return new MyPostResponseDto(post.get());
    }

    public Post savePostService(PostDto.PostWrittenRequestDto postWrittenRequestDto){
        Post post = new Post(postWrittenRequestDto);
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
