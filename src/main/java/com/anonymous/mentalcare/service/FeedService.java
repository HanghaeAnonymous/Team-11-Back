package com.anonymous.mentalcare.service;

import com.anonymous.mentalcare.dto.FeedResponseDto;
import com.anonymous.mentalcare.models.Comment;
import com.anonymous.mentalcare.models.Post;
import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.CommentRepository;
import com.anonymous.mentalcare.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public FeedResponseDto getFeed(User user) {
        List<Post> postList = postRepository.findAllByUser(user);
        List<Comment> commentList = commentRepository.findAllByUser(user);

        return new FeedResponseDto(postList, commentList);
    }
}
