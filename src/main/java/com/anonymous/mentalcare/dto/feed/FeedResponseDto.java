package com.anonymous.mentalcare.dto.feed;

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
