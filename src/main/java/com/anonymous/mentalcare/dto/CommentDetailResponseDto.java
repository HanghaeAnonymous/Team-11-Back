package com.anonymous.mentalcare.dto;


import com.anonymous.mentalcare.models.Post;
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

    public CommentDetailResponseDto(Post post, List<FeedCommentResponseDto> comments) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = comments;
    }

}

