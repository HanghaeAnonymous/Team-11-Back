package com.anonymous.mentalcare.dto;

import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.util.DateFormatChanger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RandomPostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String createdAt;
    private List<RandomPostCommentResponseDto> comments;

    public RandomPostResponseDto(Post post, List<RandomPostCommentResponseDto> comments) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = DateFormatChanger.dateFormatChange(post.getCreatedAt());
        this.comments = comments;
    }
}
