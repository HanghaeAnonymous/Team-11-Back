package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.MyPostResponseDto;
import com.anonymous.mentalcare.dto.PostDto;
import com.anonymous.mentalcare.dto.RandomPostResponseDto;
import com.anonymous.mentalcare.dto.image.ImageDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.ImageService;
import com.anonymous.mentalcare.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Api(tags = {"포스팅"})
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ImageService imageService;

    @ApiOperation(value = "랜덤 게시글 불러오기")
    @GetMapping("/api/posts")
    public ResponseEntity<RandomPostResponseDto> getRandomPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        RandomPostResponseDto randomPostResponseDto = postService.getRandomPost(userDetails.getUser());
        return ResponseEntity.ok()
                .body(randomPostResponseDto);
    }

    @ApiOperation(value = "나의 특정 게시글 불러오기")
    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<MyPostResponseDto> getMyPost(@PathVariable Long postId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MyPostResponseDto myPostResponseDto = postService.getMyPost(postId, userDetails.getUser());
        return ResponseEntity.ok()
                .body(myPostResponseDto);
    }

    @ApiOperation(value = "게시판 글 올리기")
    @PostMapping("/api/posts")//난중에 userDetails로 User도 같이 넣어줘야함 일단 테스트 용도로 User빼고 작성
    public ResponseEntity<String> savePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestBody PostDto.PostWrittenRequestDto postWrittenRequestDto) {
        postService.savePostService(postWrittenRequestDto, userDetails);
        return ResponseEntity.ok()
                .body("게시글 작성 성공");
    }

    @ApiOperation(value = "게시판 글 수정")
    @PutMapping("/api/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId,
                                             @RequestBody PostDto.PostUpdateRequestDto postUpdateRequestDto) {
        String result = postService.updatePostService(postId, postUpdateRequestDto);
        return ResponseEntity.ok()
                .body(result);
    }

    @ApiOperation(value = "게시판 글 삭제")
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok()
                .body("게시글 삭제 성공");
    }

    @ApiOperation(value = "이미지 저장")
    @PostMapping("/api/images")//난중에 userDetails로 User도 같이 넣어줘야함 일단 테스트 용도로 User빼고 작성
    public ResponseEntity<ImageDto> imageTest(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok()
                .body(imageService.imageUpload(file));
    }
}
