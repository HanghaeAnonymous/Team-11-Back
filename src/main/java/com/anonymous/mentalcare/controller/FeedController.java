package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.feed.MyPostResponseDto;
import com.anonymous.mentalcare.dto.comment.CommentDetailResponseDto;
import com.anonymous.mentalcare.dto.feed.FeedResponseDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.CommentService;
import com.anonymous.mentalcare.service.FeedService;
import com.anonymous.mentalcare.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"피드"})
@RestController
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @ApiOperation(value = "내가 작성한 글, 댓글 확인하기")
    @GetMapping("/api/feeds")
    public ResponseEntity<FeedResponseDto> getFeed(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        FeedResponseDto feedResponseDto = feedService.getFeed(userDetails);

        return ResponseEntity.ok()
                .body(feedResponseDto);
    }

    @ApiOperation(value = "나의 특정 게시글 불러오기")
    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<MyPostResponseDto> getMyPost(@PathVariable Long postId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MyPostResponseDto myPostResponseDto = feedService.getMyPost(postId, userDetails);
        return ResponseEntity.ok()
                .body(myPostResponseDto);
    }

    // 특정 댓글로 게시글 조회
    @ApiOperation(value = "내가 작성한 댓글 ID로 게시글 조회하기")
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDetailResponseDto> commentDetail(@PathVariable Long commentId,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentDetailResponseDto commentDetailResponseDto = feedService.readCommentsDetail(commentId, userDetails);

        return ResponseEntity.ok()
                .body(commentDetailResponseDto);
    }
}
