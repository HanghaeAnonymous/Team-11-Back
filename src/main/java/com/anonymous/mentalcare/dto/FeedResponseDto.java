package com.anonymous.mentalcare.dto;

import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FeedResponseDto {
    List<FeedPostResponseDto> myPosts;
    List<FeedCommentResponseDto> myComments;

    public FeedResponseDto(List<FeedPostResponseDto> postList, List<FeedCommentResponseDto> commentList) {
        this.myPosts = postList;
        this.myComments = commentList;
    }
}
