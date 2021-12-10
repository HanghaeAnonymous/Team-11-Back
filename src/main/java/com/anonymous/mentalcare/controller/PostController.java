package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.post.PostDto;
import com.anonymous.mentalcare.dto.post.RandomPostResponseDto;
import com.anonymous.mentalcare.dto.image.ImageDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
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

    @ApiOperation(value = "랜덤 게시글 불러오기")
    @GetMapping("/api/posts")
    public ResponseEntity<RandomPostResponseDto> getRandomPost(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        RandomPostResponseDto randomPostResponseDto = postService.getRandomPost(userDetails);
        return ResponseEntity.ok()
                .body(randomPostResponseDto);
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
    public ResponseEntity<String> deletePost(@PathVariable Long postId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);
        return ResponseEntity.ok()
                .body("게시글 삭제 성공");
    }

    @ApiOperation(value = "이미지 저장")
    @PostMapping("/api/images")//난중에 userDetails로 User도 같이 넣어줘야함 일단 테스트 용도로 User빼고 작성
    public ResponseEntity<ImageDto> imageTest(@RequestParam("file") MultipartFile file) throws IOException {

        String path = "/image/";
        String saveLocation = "/Users/jeong-yeongbin/Desktop/project/Team-11-Back/src/main/resources/static/image/";

        // 같은 이름의 이미지 파일을 방지하고자 램덤함 UUID를 생성해서 파일이름앞에 붙힌다.
        UUID uuid = UUID.randomUUID();
        String originFileName = file.getOriginalFilename();

        originFileName = originFileName.replace(" .", ".");

        String fileName = uuid + "_" + originFileName;

        file.transferTo(new File(saveLocation + fileName));

        path += fileName;
        path = path.replace(" .", ".");

        ImageDto imageDto = ImageDto.builder().imageUrl(path).build();

        return ResponseEntity.ok()
                .body(imageDto);
    }
}
