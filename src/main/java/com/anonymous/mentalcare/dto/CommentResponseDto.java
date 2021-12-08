package com.anonymous.mentalcare.dto;

import com.anonymous.mentalcare.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getComment();
    }
}
