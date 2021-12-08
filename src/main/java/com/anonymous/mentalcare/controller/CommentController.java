package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.CommentRequestDto;
import com.anonymous.mentalcare.dto.FeedCommentResponseDto;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comments/{postId}")
    public void comment(@PathVariable Long postId,
                        @AuthenticationPrincipal UserDetailsImpl userDetails,
                        @RequestBody CommentRequestDto commentRequestDto){
        commentService.comment(postId, commentRequestDto, userDetails);
    }
    // 특정 댓글로 게시글 조회
    @GetMapping("/api/comments/{commentId}")
    public CommentDetailResponseDto commentDetail(@PathVariable Long commentId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDtails) {
        return  commentService.readCommentsDetail(commentId,userDtails.getUser());
    }

}
