package com.anonymous.mentalcare.dto.comment;


import com.anonymous.mentalcare.dto.feed.FeedCommentResponseDto;
import com.anonymous.mentalcare.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailResponseDto {
    private String title;
    private String content;
    private List<FeedCommentResponseDto> comments;

    public CommentDetailResponseDto(Comment comment, List<FeedCommentResponseDto> comments) {
        this.title = comment.getPost().getTitle();
        this.content = comment.getPost().getContent();
        this.comments = comments;
    }


}

