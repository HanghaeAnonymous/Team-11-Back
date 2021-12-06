package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.PostResponseDto;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto getRandomPost() {
        // 랜덤한 게시글 받아오기
        // findAll 하면 전체 목록을 받아오기때문에 리소스 낭비가 심하다.
        // 페이징 기법으로 내용을 게시글이 1개인 모든 페이지로 만들고 랜덤인덱스로 가져오면 된다.
        long qty = postRepository.count();
        int idx = (int)(Math.random() * qty);
        Page<Post> postPage = postRepository.findAll(PageRequest.of(idx, 1));

        PostResponseDto postResponseDto = null;
        if (postPage.hasContent()) {
            postResponseDto = new PostResponseDto(postPage.getContent().get(0));
        }

        return postResponseDto;
    }
}