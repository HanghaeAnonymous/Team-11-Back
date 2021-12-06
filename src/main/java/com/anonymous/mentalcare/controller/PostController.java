package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.PostResponseDto;
import com.anonymous.mentalcare.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/api/posts")
    public PostResponseDto getRandomPost(){
        return postService.getRandomPost();
    }
}
