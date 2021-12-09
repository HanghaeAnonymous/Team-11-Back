package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.comment.CommentDetailResponseDto;
import com.anonymous.mentalcare.dto.comment.CommentRequestDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"댓글"})
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @ApiOperation(value = "댓글 작성하기")
    @PostMapping("/api/comments/{postId}")
    public ResponseEntity<String> comment(@PathVariable Long postId,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails,
                                          @RequestBody CommentRequestDto commentRequestDto) {
        commentService.comment(postId, commentRequestDto, userDetails);

        return ResponseEntity.ok()
                .body("댓글 작성 완료!");
    }

    // 특정 댓글로 게시글 조회
    @ApiOperation(value = "댓글 ID로 게시글 조회하기")
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDetailResponseDto> commentDetail(@PathVariable Long commentId,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentDetailResponseDto commentDetailResponseDto = commentService.readCommentsDetail(commentId, userDetails.getUser());

        return ResponseEntity.ok()
                .body(commentDetailResponseDto);
    }
}
