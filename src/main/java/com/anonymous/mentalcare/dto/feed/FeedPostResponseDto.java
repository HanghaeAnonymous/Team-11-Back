package com.anonymous.mentalcare.dto.feed;

import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.util.DateFormatChanger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedPostResponseDto{
    private Long postId;
    private String title;
    private String content;
    private String createdAt;

    public FeedPostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = DateFormatChanger.dateFormatChange(post.getCreatedAt());
    }
}
