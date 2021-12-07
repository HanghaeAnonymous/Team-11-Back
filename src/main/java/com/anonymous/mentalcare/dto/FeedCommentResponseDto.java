package com.anonymous.mentalcare.dto;

import com.anonymous.mentalcare.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedCommentResponseDto {
    private Long commentId;
    private String comment;
    private LocalDateTime createdAt;

    public FeedCommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}
