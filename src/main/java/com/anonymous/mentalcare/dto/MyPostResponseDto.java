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
public class MyPostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String createdAt;
    private List<MyPostCommentResponseDto> comments;

    public MyPostResponseDto(Post post, List<MyPostCommentResponseDto> comments) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = comments;
        this.createdAt = DateFormatChanger.dateFormatChange(post.getCreatedAt());
    }
}
