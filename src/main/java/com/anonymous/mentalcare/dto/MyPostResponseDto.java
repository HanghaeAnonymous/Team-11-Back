package com.anonymous.mentalcare.dto;

import com.anonymous.mentalcare.models.Comment;
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
public class MyPostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private List<RandomPostCommentResponseDto> comments;

    public MyPostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        for(Comment comment:post.getCommentList()){
            this.comments.add(new RandomPostCommentResponseDto(comment));
        }
    }
}
