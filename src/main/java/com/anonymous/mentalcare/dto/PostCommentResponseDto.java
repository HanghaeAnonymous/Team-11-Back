package com.anonymous.mentalcare.dto;

import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.util.DateFormatChanger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentResponseDto {
    private Long commentId;
    private String comment;
    private String createdAt;

    public PostCommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.createdAt = DateFormatChanger.dateFormatChange(comment.getCreatedAt());
    }
}
