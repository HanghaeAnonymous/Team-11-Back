package com.anonymous.mentalcare.controller;

import com.anonymous.mentalcare.dto.comment.CommentRequestDto;
import com.anonymous.mentalcare.exception.RestApiException;
import com.anonymous.mentalcare.security.UserDetailsImpl;
import com.anonymous.mentalcare.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<RestApiException> exceptionHandler(Exception e) {
        return ResponseEntity.badRequest()
                .body(new RestApiException(e.getMessage(), HttpStatus.BAD_REQUEST));
    }
}
