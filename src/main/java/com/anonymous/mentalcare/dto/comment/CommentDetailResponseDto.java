package com.anonymous.mentalcare.dto.comment;


import com.anonymous.mentalcare.dto.feed.FeedCommentResponseDto;
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
public class CommentDetailResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String createdAt;
    private List<FeedCommentResponseDto> comments = new ArrayList<>();

    public CommentDetailResponseDto(Comment comment) {
        Post post = comment.getPost();

        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = DateFormatChanger.dateFormatChange(post.getCreatedAt());

        for(Comment postComment : post.getCommentList()){
            this.comments.add(new FeedCommentResponseDto(postComment));
        }
    }
}

