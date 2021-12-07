package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.PostDto;
import com.anonymous.mentalcare.dto.PostResponseDto;
import com.anonymous.mentalcare.dto.RandomPostResponseDto;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/api/posts")
    public RandomPostResponseDto getRandomPost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.getRandomPost(userDetails.getUser());
    }

    @PostMapping("/api/posts")//난중에 userDetails로 User도 같이 넣어줘야함 일단 테스트 용도로 User빼고 작성
    public Post savePost(@RequestBody PostDto.PostWrittenReqeustDto postWrittenReqeustDto){
        return postService.savePostService(postWrittenReqeustDto);
    }

    @PutMapping("/api/posts/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestBody PostDto.PostUpdateRequestDto postUpdateRequestDto){
        return postService.updataePostService(postId,postUpdateRequestDto);
    }

    @DeleteMapping("/api/posts/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }
}
