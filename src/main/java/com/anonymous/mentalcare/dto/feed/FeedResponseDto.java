package com.anonymous.mentalcare.dto.feed;

import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FeedResponseDto {
    List<FeedPostResponseDto> myPosts = new ArrayList<>();
    List<FeedCommentResponseDto> myComments = new ArrayList<>();

    public FeedResponseDto(List<Post> postList, List<Comment> commentList) {
        for(Post post: postList){
            this.myPosts.add(new FeedPostResponseDto(post));
        }
        for(Comment comment: commentList){
            this.myComments.add(new FeedCommentResponseDto(comment));
        }
    }
}
