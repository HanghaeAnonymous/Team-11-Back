package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.feed.FeedCommentResponseDto;
import com.anonymous.mentalcare.dto.feed.FeedPostResponseDto;
import com.anonymous.mentalcare.dto.feed.FeedResponseDto;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public FeedResponseDto getFeed(User user) {
        List<Post> postList = postRepository.findAllByUser(user);
        List<Comment> commentList = commentRepository.findAllByUser(user);

        List<FeedPostResponseDto> feedPostResponseDtoList = new ArrayList<>();
        List<FeedCommentResponseDto> feedCommentResponseDtoList = new ArrayList<>();

        System.out.println("postList Size : " + postList.size());
        System.out.println("commentList Size : " + commentList.size());
        for (Post post : postList) {
            feedPostResponseDtoList.add(new FeedPostResponseDto(post));
        }
        for (Comment comment : commentList) {
            feedCommentResponseDtoList.add(new FeedCommentResponseDto(comment));
        }

        return new FeedResponseDto(feedPostResponseDtoList, feedCommentResponseDtoList);
    }
}
