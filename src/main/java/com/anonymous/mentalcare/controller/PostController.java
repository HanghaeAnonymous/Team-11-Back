package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.PostDto;
import com.anonymous.mentalcare.dto.PostResponseDto;
import com.anonymous.mentalcare.dto.RandomPostResponseDto;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"포스팅"})
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/api/posts")
    public RandomPostResponseDto getRandomPost(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails);
        return postService.getRandomPost(userDetails.getUser());
    }

    @ApiOperation(value = "게시판 글 올리기")
    @PostMapping("/api/posts")//난중에 userDetails로 User도 같이 넣어줘야함 일단 테스트 용도로 User빼고 작성
    public Post savePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostDto.PostWrittenRequestDto postWrittenRequestDto){
        return postService.savePostService(postWrittenRequestDto,userDetails);
    }

    @PutMapping("/api/posts/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestBody PostDto.PostUpdateRequestDto postUpdateRequestDto){
        return postService.updatePostService(postId,postUpdateRequestDto);
    }

    @DeleteMapping("/api/posts/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }
}
