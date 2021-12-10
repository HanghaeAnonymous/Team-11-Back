package com.anonymous.mentalcare.dto.feed;

import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.util.DateFormatChanger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private String imageUrl;
    private List<MyPostCommentResponseDto> comments = new ArrayList<>();

    public MyPostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.createdAt = DateFormatChanger.dateFormatChange(post.getCreatedAt());

        for(Comment comment: post.getCommentList()){
            this.comments.add(new MyPostCommentResponseDto(comment));
        }
    }
}
